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


public class PineconeEmbeddingsStore extends EmbeddingsStore {

    private final String url;
    private final String apiKey;
    private final String namespace;
    private static OkHttpClient client;
    public PineconeEmbeddingsStore() {
        url = System.getenv("PINECONE_URL");
        apiKey = System.getenv("PINECONE_API_KEY");
        namespace = System.getenv("PINECONE_NAMESPACE");

        if (url == null) {
            throw new EnvironmentVariableNotDeclaredException("Environement variable PINECONE_URL is not declared.");
        } else if (apiKey == null) {
            throw new EnvironmentVariableNotDeclaredException("Environement variable PINECONE_API_KEY is not declared.");
        } else if (namespace == null) {
            // TODO: need to test code using pinecone with namespace
            throw new EnvironmentVariableNotDeclaredException("Environement variable PINECONE_NAMESPACE is not declared.");
        }

        client = new OkHttpClient();
    }

    private String post(String path, String bodyString) throws IOException {
        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(bodyString, JSON);

        Request request = new Request.Builder()
                .url(url + path)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("Api-Key", apiKey)
                .post(body)
                .build();

        try(Response response = client.newCall(request).execute()) {
            if (response.code() >= HTTP_BAD_REQUEST) {
                throw new IOException(url);
            }

            ResponseBody responseBody = response.body();

            if ( responseBody != null ) {
                return responseBody.string();
            } else {
                return null;
            }
        }
    }

    public void store(Embedding embedding, Map<String,String> metadata) {
        UpsertVectorSchema schema = new UpsertVectorSchema(embedding, metadata);
        String requestBodyJson = new Gson().toJson(schema);

        try  {
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
                reference.vector,
                null
        );
        String requestBodyJson = new Gson().toJson(body);

        try  {
            String responseString = this.post("/query", requestBodyJson);
            QueryVectorResponseSchema response = new Gson().fromJson(responseString, QueryVectorResponseSchema.class);
            ArrayList<Embedding> matchEmbeddings = new ArrayList<>();

            for ( Match m : response.matches) {
                matchEmbeddings.add(new Embedding(m.values));
            }

            return matchEmbeddings;
        } catch (IOException e) {
            System.out.println("VectorStore error on upsert: " + e.getMessage());
            return null;
        }
    }
}
