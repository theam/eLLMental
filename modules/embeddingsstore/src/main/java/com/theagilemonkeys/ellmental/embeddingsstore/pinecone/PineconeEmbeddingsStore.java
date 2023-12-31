package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;

import com.google.gson.Gson;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.core.errors.MissingRequiredCredentialException;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

/**
 * Implementation for Pinecone EmbeddingsStore
 */
@RequiredArgsConstructor
public class PineconeEmbeddingsStore extends EmbeddingsStore {
    private static final Logger log = LoggerFactory.getLogger(PineconeEmbeddingsStore.class);

    private final String url;
    private final String apiKey;
    private String namespace;
    // This attribute has no modifier to be package-private, so it can be mocked in tests.
    // This field will be accessible within this package, but not outside of it.
    // TODO: Check if MockWebServer from OkHttpClient can simplify this:
    // https://github.com/square/okhttp/blob/master/mockwebserver-junit5/README.md
    OkHttpClient httpClient = new OkHttpClient();

    /**
     * Constructor that initializes the Pinecone embeddings store with an explicit url, apiKey and namespace.
     *
     * @param url Pinecone URL.
     * @param apiKey Pinecone API key.
     * @param namespace Pinecone namespace.
     */
    public PineconeEmbeddingsStore(String url, String apiKey, String namespace) {
        this.url = url;
        this.apiKey = apiKey;
        this.namespace = namespace;
    }

    /**
     * Stores an embedding in the embeddings store.
     * @param embedding Embedding object to store.
     */
    public void store(Embedding embedding) {
        UpsertVectorSchema schema = new UpsertVectorSchema(embedding, this.namespace);
        String requestBodyJson = new Gson().toJson(schema);

        try {
            log.debug("Inserting embedding with request: {}", requestBodyJson);
            this.post("/vectors/upsert", requestBodyJson);
        } catch (IOException e) {
            log.error("VectorStore error on upsert: {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    /**
     * Retrieves and embedding in the embeddings store based on its UUID.
     * @param uuid UUID to retrieve.
     */
    public Embedding get(UUID uuid) {
        Embedding embedding = null;
        try {
            log.debug("Getting embedding with UUID {} and namespace {}", uuid, this.namespace);
            embedding = this.fetch(List.of(uuid));
        } catch (IOException e) {
            log.error("VectorStore error on fetch: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.debug("Got embedding {}", embedding);
        return embedding;
    }

    /**
     * Deletes an embedding in the embeddings store based on its UUID.
     * @param uuid UUID to delete.
     */
    public void delete(UUID uuid) {
        DeleteVectorSchema request = new DeleteVectorSchema(List.of(uuid));
        request.setNamespace(this.namespace);
        String requestBodyJson = new Gson().toJson(request);

        try {
            log.debug("Deleting embedding with request: {}", requestBodyJson);
            this.post("/vectors/delete", requestBodyJson);
        } catch (IOException e) {
            log.error("VectorStore error on delete: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public List<Embedding> similaritySearch(Embedding reference, int limit) {
        QueryVectorRequestSchema body = new QueryVectorRequestSchema(
                limit,
                true,
                true,
                reference.vector,
                this.namespace);
        String requestBodyJson = new Gson().toJson(body);

        try {
            log.debug("Getting similar embeddings with request: {}", requestBodyJson);
            String responseString = this.post("/query", requestBodyJson);
            QueryVectorResponseSchema response = new Gson().fromJson(responseString, QueryVectorResponseSchema.class);

            log.debug("Got the following embeddings as a response: {}", response);
            // Sort the matches by similarity using the score field and map them to Embedding objects
            return response
                    .matches
                    .stream()
                    .sorted((a, b) -> Double.compare(b.score, a.score))
                    .map(match -> new Embedding(match.id, match.values, match.metadata, match.score))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("VectorStore error on upsert: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private boolean validateEnvironment() {
        log.debug("Validating environment variables");
        if (url == null) {
            throw new MissingRequiredCredentialException("A Pinecone URL must be set.");
        } else if (apiKey == null) {
            throw new MissingRequiredCredentialException("Pinecone API key is required.");
        }
        // TODO: check if namespace is required. From Pinecone documentation this doesn't seem to be the case, so I removed the check here.
        return true;
    }

    private Embedding fetch(List<UUID> ids) throws IOException {
        if (!validateEnvironment()) {
            return null;
        }

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(this.url + "/vectors/fetch")).newBuilder();
        urlBuilder.addQueryParameter("ids", ids.toString());
        if (this.namespace != null) {
            urlBuilder.addQueryParameter("namespace", this.namespace);
        }

        HttpUrl url = urlBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("Api-Key", apiKey)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() >= HTTP_BAD_REQUEST) {
                throw new IOException(response.toString());
            }

            ResponseBody responseBody = response.body();

            if (responseBody != null) {
                String json = responseBody.string();
                FetchVectorResponseSchema responseSchema = new Gson().fromJson(json, FetchVectorResponseSchema.class);
                Vector vector = responseSchema.getVectors().get(0);
                return new Embedding(vector.getId(), vector.getValues(), vector.getMetadata());
            } else {
                return null;
            }
        }
    }

    private String post(String path, String bodyString) throws IOException {
        if (!validateEnvironment()) {
            return null;
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(bodyString, JSON);

        Request request = new Request.Builder()
                .url(url + path)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("Api-Key", apiKey)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() >= HTTP_BAD_REQUEST) {
                throw new IOException(response.toString());
            }

            ResponseBody responseBody = response.body();

            if (responseBody != null) {
                return responseBody.string();
            } else {
                return null;
            }
        }
    }
}
