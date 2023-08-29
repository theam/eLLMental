package com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.theagilemonkeys.ellmental.core.actionhandlers.ActionHandler;
import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.common.Match;
import com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.common.PineconeService;
import com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.common.QueryVectorRequestSchema;
import com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.common.QueryVectorResponseSchema;
import com.theagilemonkeys.ellmental.embeddingsstore.actions.SimilaritySearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.theagilemonkeys.ellmental.core.WorkerManager.registerActionHandler;

public class SimilaritySearchHandler implements ActionHandler {
    private String baseUrl;
    private String apiKey;
    private PineconeService pineconeService;

    public SimilaritySearchHandler(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.pineconeService = new PineconeService(baseUrl, apiKey);
    }

    public static void use(String baseUrl, String apiKey) {
        registerActionHandler(
                SimilaritySearch.name,
                new SimilaritySearchHandler(baseUrl, apiKey)
        );
    }

    @Override
    public JsonElement handle(JsonElement actionJson) {
        // Parse the input
        var gson = new Gson();
        var input = gson.fromJson(actionJson, SimilaritySearch.ActionInput.class);
        var reference = input.reference();
        var limit = input.limit();

        // Generate the embedding
        var result = similaritySearch(reference, limit);

        // Return the result as JSON
        return gson.toJsonTree(result);
    }


    public List<Embedding> similaritySearch(Embedding reference, int limit) {
        QueryVectorRequestSchema body = new QueryVectorRequestSchema(
                limit,
                true,
                true,
                reference.vector,
                null);
        String requestBodyJson = new Gson().toJson(body);

        try {
            String responseString = this.pineconeService.post("/query", requestBodyJson);
            QueryVectorResponseSchema response = new Gson().fromJson(responseString, QueryVectorResponseSchema.class);
            ArrayList<Embedding> matchEmbeddings = new ArrayList<>();

            for (Match m : response.matches()) {
                matchEmbeddings.add(new Embedding(m.values()));
            }

            return matchEmbeddings;
        } catch (IOException e) {
            System.out.println("VectorStore error on upsert: " + e.getMessage());
            return null;
        }
    }
}
