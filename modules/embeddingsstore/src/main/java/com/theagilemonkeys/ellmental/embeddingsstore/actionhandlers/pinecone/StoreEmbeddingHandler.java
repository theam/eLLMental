package com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.theagilemonkeys.ellmental.core.actionhandlers.ActionHandler;
import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.common.PineconeService;
import com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.common.UpsertVectorSchema;
import com.theagilemonkeys.ellmental.embeddingsstore.actions.StoreEmbedding;

import java.io.IOException;
import java.util.Map;

import static com.theagilemonkeys.ellmental.core.WorkerManager.registerActionHandler;

public class StoreEmbeddingHandler implements ActionHandler {
    private String baseUrl;
    private String apiKey;
    private PineconeService pineconeService;

    public StoreEmbeddingHandler(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.pineconeService = new PineconeService(baseUrl, apiKey);
    }

    public static void use(String baseUrl, String apiKey) {
        registerActionHandler(
                StoreEmbedding.name,
                new StoreEmbeddingHandler(baseUrl, apiKey)
        );
    }

    @Override
    public JsonElement handle(JsonElement actionJson) {
        // Parse the input
        var gson = new Gson();
        var input = gson.fromJson(actionJson, StoreEmbedding.ActionInput.class);
        var embedding = input.embedding();
        var metadata = input.metadata();

        store(embedding, metadata);

        // Return the result as JSON
        return JsonNull.INSTANCE;
    }

    public void store(Embedding embedding, Map<String, String> metadata) {
        var schema = UpsertVectorSchema.of(embedding, metadata);
        var requestBodyJson = new Gson().toJson(schema);

        try {
            this.pineconeService.post("/vectors/upsert", requestBodyJson);
        } catch (IOException e) {
            System.out.println("VectorStore error on upsert: " + e.getMessage());
        }

    }

}
