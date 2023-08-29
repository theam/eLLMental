package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;

import com.google.gson.Gson;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.core.errors.EnvironmentVariableNotDeclaredException;
import okhttp3.*;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Implementation for Pinecone EmbeddingsStore
 */
public class PineconeEmbeddingsStore extends EmbeddingsStore {
    private final String url;
    private final String apiKey;
    private final String namespace;

    /**
     * Constructor that initializes the Pinecone embeddings store loading the configuration from environment variables:
     *  It will load the API key from the environment variable `PINECONE_API_KEY`.
     *  It will load the URL from the environment variable `PINECONE_URL`.
     *  It will load the namespace from the environment variable `PINECONE_NAMESPACE`.
     */
    public PineconeEmbeddingsStore() {
        this(null, null, null);
    }

    /**
     * Constructor that initializes the Pinecone embeddings store with an explicit url, apiKey and namespace.
     *
     * @param url Pinecone URL.
     * @param apiKey Pinecone API key.
     * @param namespace Pinecone namespace.
     */
    public PineconeEmbeddingsStore(String url, String apiKey, String namespace) {
        if (url == null || apiKey == null || namespace == null) {
            var dotenv = Dotenv
                    .configure()
                    .ignoreIfMissing()
                    .ignoreIfMalformed()
                    .load();
            if (url == null) url = dotenv.get("PINECONE_URL");
            if (apiKey == null) apiKey = dotenv.get("PINECONE_API_KEY");
            if (namespace == null) namespace = dotenv.get("PINECONE_NAMESPACE");
        }
        this.url = url;
        this.apiKey = apiKey;
        this.namespace = namespace;
    }

    /**
     * Stores an embedding in the embeddings store.
     * @param embedding Embedding object to store.
     */
    public void store(Embedding embedding) {
        UpsertVectorSchema schema = new UpsertVectorSchema(embedding);
        String requestBodyJson = new Gson().toJson(schema);

        try {
            this.post("/vectors/upsert", requestBodyJson);
        } catch (IOException e) {
            System.out.println("VectorStore error on upsert: " + e.getMessage());
        }

    }

    public List<Embedding> similaritySearch(Embedding reference, int limit) {
        QueryVectorRequestSchema body = new QueryVectorRequestSchema(
                limit,
                true,
                true,
                reference.vector(),
                namespace);
        String requestBodyJson = new Gson().toJson(body);

        try {
            String responseString = this.post("/query", requestBodyJson);
            QueryVectorResponseSchema response = new Gson().fromJson(responseString, QueryVectorResponseSchema.class);
            ArrayList<Embedding> matchEmbeddings = new ArrayList<>();

            for (Match match : response.matches) { // TODO: Make sure this array is sorted by similarity using the score field
                matchEmbeddings.add(new Embedding(match.id, match.values, match.metadata));
            }

            return matchEmbeddings;
        } catch (IOException e) {
            System.out.println("VectorStore error on upsert: " + e.getMessage());
            return null;
        }
    }

    private boolean validateEnvironment() {
        if (url == null) {
            throw new EnvironmentVariableNotDeclaredException("Environment variable PINECONE_URL is not declared.");
        } else if (apiKey == null) {
            throw new EnvironmentVariableNotDeclaredException(
                    "Environment variable PINECONE_API_KEY is not declared.");
        }
        // TODO: check if namespace is required. From Pinecone documentation this doesn't seem to be the case, so I removed the check here.
        return true;
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

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.code() >= HTTP_BAD_REQUEST) {
                throw new IOException(url);
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
