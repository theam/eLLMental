package com.theagilemonkeys.ellmental.embeddingsgeneration.openai.worker.actionhandlers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.theagilemonkeys.ellmental.core.actionhandlers.ActionHandler;
import com.theagilemonkeys.ellmental.core.errors.EnvironmentVariableNotDeclaredException;
import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.openai.worker.actions.GenerateEmbedding;
import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.service.OpenAiService;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.List;

public class GenerateEmbeddingHandler implements ActionHandler {
    private final Gson gson = new Gson();
    private static OpenAiService service;


    public OpenAiService getService() {
        if (service == null) {
            var dotenv = Dotenv
                    .configure()
                    .ignoreIfMissing()
                    .ignoreIfMalformed()
                    .load();
            String openAIKey = dotenv.get("OPEN_AI_API_KEY");
            if (openAIKey == null) {
                throw new EnvironmentVariableNotDeclaredException("Environment variable OPEN_AI_API_KEY is not declared.");
            }
            service = new OpenAiService(openAIKey);
        }
        return service;
    }

    @Override
    public JsonElement handle(JsonElement actionJson) {
        // Parse the input
        var input = gson.fromJson(actionJson, GenerateEmbedding.ActionInput.class);
        var inputString = input.text();
        var embeddingOpenAiModel = input.model();


        // TODO: the embeddings function from the library uses an array as input. We are
        // only using a length 1 array.
        // Check if we should implement an array option.
        List<String> embeddingsInput = new ArrayList<>();
        embeddingsInput.add(inputString);

        EmbeddingRequest embeddingRequest = EmbeddingRequest.builder()
                .model(embeddingOpenAiModel)
                .input(embeddingsInput)
                .build();

        List<Double> embedding = this.getService()
                .createEmbeddings(embeddingRequest)
                .getData()
                .get(0)
                .getEmbedding();

        var result = new Embedding(embedding);
        return gson.toJsonTree(result);
    }
}
