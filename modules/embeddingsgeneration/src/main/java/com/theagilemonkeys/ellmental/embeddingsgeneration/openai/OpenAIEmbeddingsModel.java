package com.theagilemonkeys.ellmental.embeddingsgeneration.openai;

import com.theagilemonkeys.ellmental.core.errors.EnvironmentVariableNotDeclaredException;
import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel;

import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.service.OpenAiService;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * OpenAI `EmbeddingsGenerationModel` implementation.
 */
public class OpenAIEmbeddingsModel extends EmbeddingsGenerationModel {
    private static String openAIKey;
    private static OpenAiService cachedService;
    public static String embeddingOpenAiModel = "text-embedding-ada-002";

    /**
     * Constructor that initializes the OpenAI embeddings model.
     * It will try to load the API key from the environment variable `OPEN_AI_API_KEY`.
     */
    public OpenAIEmbeddingsModel() {
        this(null);
    }

    /**
     * Constructor that initializes the OpenAI embeddings model with an explicit API Key.
     *
     * @param openAIKey OpenAI API key.
     */
    public OpenAIEmbeddingsModel(String APIKey) {
        if (APIKey != null) {
            openAIKey = APIKey;
        } else { // Tries to load it from the environment variable
            var dotenv = Dotenv
                    .configure()
                    .ignoreIfMissing()
                    .ignoreIfMalformed()
                    .load();
            openAIKey = dotenv.get("OPEN_AI_API_KEY");
        }
    }

    /**
     * Generates an embedding for the given input string.
     *
     * @param inputString Input string to generate the embedding for.
     * @return An embedding object for the given text.
     */
    public Embedding generateEmbedding(String inputString) {
        /* TODO: the embeddings function from the library uses an array as input.
            We are only using a length 1 array. Should implement an array option? */
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
        if (cachedService == null) {
            if (openAIKey == null) {
                throw new EnvironmentVariableNotDeclaredException("Environment variable OPEN_AI_API_KEY is required.");
            }
            cachedService = new OpenAiService(openAIKey);
        }
        return cachedService;
    }
}
