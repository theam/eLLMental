package com.theagilemonkeys.ellmental.embeddingsgeneration.openai;

import com.theagilemonkeys.ellmental.core.errors.MissingRequiredCredentialException;
import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel;

import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * OpenAI `EmbeddingsGenerationModel` implementation.
 */
@RequiredArgsConstructor
public class OpenAIEmbeddingsModel extends EmbeddingsGenerationModel {
    private static final Logger log = LoggerFactory.getLogger(OpenAIEmbeddingsModel.class);
    private final String openAIKey;
    public static String embeddingOpenAiModel = "text-embedding-ada-002";

    // This attribute needs no modifier to allow injection from tests,
    // It is accessible for other classes in this package, but won't be accessible to end users.
    OpenAiService openAiService;

    /**
     * Generates an embedding for the given input string.
     *
     * @param inputString Input string to generate the embedding for.
     * @return An embedding object for the given text.
     */
    public Embedding generateEmbedding(String inputString) {
        /* TODO: the embeddings function from the library uses an array as input.
            We are only using a length 1 array. Should implement an array option? */
        log.debug("Generating embedding for string {}", inputString);

        List<String> embeddingsInput = new ArrayList<>();
        embeddingsInput.add(inputString);

        EmbeddingRequest embeddingRequest = EmbeddingRequest.builder()
                .model(embeddingOpenAiModel)
                .input(embeddingsInput)
                .build();

        List<Double> vector = getService()
                .createEmbeddings(embeddingRequest)
                .getData()
                .get(0)
                .getEmbedding();

        Map<String, String> metadata = new java.util.HashMap<>();
        metadata.put("input", inputString);
        metadata.put("source", "OpenAI");
        metadata.put("model", embeddingOpenAiModel);
        metadata.put("createdAt", java.time.LocalDateTime.now().toString());
        return new Embedding(UUID.randomUUID(), vector, metadata);
    }

    private OpenAiService getService() {
        log.debug("Creating openAI service");
        if (openAiService == null) {
            if (openAIKey == null) {
                log.error("OpenAI API key is missing!");
                throw new MissingRequiredCredentialException("OpenAI API key is required.");
            }
            openAiService = new OpenAiService(openAIKey);
        }
        return openAiService;
    }
}
