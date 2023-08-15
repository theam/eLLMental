package com.theagilemonkeys.ellmental.embeddingsgeneration.openai;

import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel;


import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.List;


public class OpenAIEmbeddingsModel extends EmbeddingsGenerationModel {

    private static OpenAiService service;
    static String embeddingOpenAiModel = "text-embedding-ada-002";
    public OpenAIEmbeddingsModel() {
        service = new OpenAiService("");
    }
    public Embedding generateEmbedding(String inputString) {

        // TODO: the embedding function uses array as input. Check if we should implement an array option.
        List<String> embeddingsInput = new ArrayList<>();
        embeddingsInput.add(inputString);


        EmbeddingRequest embeddingRequest = EmbeddingRequest.builder()
                .model(embeddingOpenAiModel)
                .input(embeddingsInput)
                .build();


        List<Double> embedding = service.createEmbeddings(embeddingRequest).getData().get(0).getEmbedding();

        Embedding embeddingReturn = new Embedding();
        embeddingReturn.vector = embedding;

        return embeddingReturn;
    }
}
