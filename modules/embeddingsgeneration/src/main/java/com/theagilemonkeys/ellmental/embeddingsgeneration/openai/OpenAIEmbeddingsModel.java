package com.theagilemonkeys.ellmental.embeddingsgeneration.openai;

import com.theagilemonkeys.ellmental.core.errors.EnvironmentVariableNotDeclaredException;
import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel;


import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.List;


public class OpenAIEmbeddingsModel extends EmbeddingsGenerationModel {
    private static OpenAiService service;
    public static String embeddingOpenAiModel = "text-embedding-ada-002";
    public OpenAIEmbeddingsModel() {
        String openAIKey = System.getenv("OPEN_AI_API_KEY");
        if (openAIKey == null) {
            throw new EnvironmentVariableNotDeclaredException("Environment variable OPEN_AI_API_KEY is not declared.");
        }
        service = new OpenAiService(openAIKey);

    }
    public Embedding generateEmbedding(String inputString) {

        // TODO: the embeddings function from the library uses an array as input. We are only using a length 1 array.
        //  Check if we should implement an array option.
        List<String> embeddingsInput = new ArrayList<>();
        embeddingsInput.add(inputString);

        EmbeddingRequest embeddingRequest = EmbeddingRequest.builder()
                .model(embeddingOpenAiModel)
                .input(embeddingsInput)
                .build();

        List<Double> embedding = service.createEmbeddings(embeddingRequest).getData().get(0).getEmbedding();

        return new Embedding(embedding);
    }
}
