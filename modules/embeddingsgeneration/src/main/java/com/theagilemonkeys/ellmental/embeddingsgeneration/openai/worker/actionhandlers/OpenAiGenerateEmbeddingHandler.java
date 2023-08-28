package com.theagilemonkeys.ellmental.embeddingsgeneration.openai.worker.actionhandlers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.theagilemonkeys.ellmental.core.actionhandlers.ActionHandler;
import com.theagilemonkeys.ellmental.core.errors.EnvironmentVariableNotDeclaredException;
import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.openai.worker.actions.GenerateEmbedding;
import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.List;

import static com.theagilemonkeys.ellmental.core.WorkerManager.registerActionHandler;

public class OpenAiGenerateEmbeddingHandler implements ActionHandler {
    private final Gson gson = new Gson();
    private static OpenAiService service;
    private static final String DEFAULT_EMBEDDING_MODEL = "text-embedding-ada-002";

    public OpenAiGenerateEmbeddingHandler(String apiKey) {
        if (apiKey == null) {
            throw new EnvironmentVariableNotDeclaredException("Environment variable OPEN_AI_API_KEY is not declared.");
        }
        service = new OpenAiService(apiKey);
    }


    public static void use(String apiKey) {
        var instance = new OpenAiGenerateEmbeddingHandler(apiKey);
        registerActionHandler(GenerateEmbedding.name, instance);
    }


    @Override
    public JsonElement handle(JsonElement actionJson) {
        // Parse the input
        var input = gson.fromJson(actionJson, GenerateEmbedding.ActionInput.class);
        var inputString = input.text();

        // Generate the embedding
        Embedding result = generateEmbedding(inputString);

        // Return the result as JSON
        return gson.toJsonTree(result);
    }

    private Embedding generateEmbedding(String inputString) {
        // TODO: the embeddings function from the library uses an array as input. We are
        // only using a length 1 array.
        // Check if we should implement an array option.
        List<String> embeddingsInput = new ArrayList<>();
        embeddingsInput.add(inputString);

        EmbeddingRequest embeddingRequest = EmbeddingRequest.builder()
                .model(OpenAiGenerateEmbeddingHandler.DEFAULT_EMBEDDING_MODEL)
                .input(embeddingsInput)
                .build();

        List<Double> embedding = service
                .createEmbeddings(embeddingRequest)
                .getData()
                .get(0)
                .getEmbedding();

        return new Embedding(embedding);
    }


}
