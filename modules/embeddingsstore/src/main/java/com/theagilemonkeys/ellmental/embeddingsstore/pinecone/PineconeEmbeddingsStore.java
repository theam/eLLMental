package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;

import com.google.gson.Gson;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



import com.theagilemonkeys.ellmental.core.schema.Embedding;
import okhttp3.*;


import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;


public class PineconeEmbeddingsStore extends EmbeddingsStore {

    private static final String url = "https://andre-index-ae44658.svc.gcp-starter.pinecone.io";
    private static OkHttpClient client;
    public PineconeEmbeddingsStore(

    ) {
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
                .header("Api-Key", "1c13987f-1d13-4373-8655-f739089de6af")
                .post(body)
                .build();

        // TODO: need to improve error handling message
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

    // TODO: using store for the upsert. Check if this is the correct path.
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

            QueryVectorRequestSchema response = new Gson().fromJson(responseString, QueryVectorRequestSchema.class);



            return Arrays.asList(new Embedding(), new Embedding());
        } catch (IOException e) {
            System.out.println("VectorStore error on upsert: " + e.getMessage());
            return null;
        }



    }
}
