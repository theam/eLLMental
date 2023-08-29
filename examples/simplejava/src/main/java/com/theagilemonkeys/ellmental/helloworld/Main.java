package com.theagilemonkeys.ellmental.helloworld;

import com.theagilemonkeys.ellmental.embeddingsgeneration.actionhandlers.OpenAiGenerateEmbeddingHandler;
import com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.SimilaritySearchHandler;
import com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.StoreEmbeddingHandler;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;
import java.util.Map;

import static com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel.generateEmbedding;
import static com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore.similaritySearch;
import static com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore.storeEmbedding;

public class Main {
    public static void main(String[] args) {
        // Step 0: initialize handlers
        var dotenv = Dotenv
                .configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();
        var url = dotenv.get("PINECONE_URL");
        var pineconeApiKey = dotenv.get("PINECONE_API_KEY");
        var openAiApiKey = dotenv.get("OPENAI_API_KEY");

        OpenAiGenerateEmbeddingHandler.use(openAiApiKey);
        StoreEmbeddingHandler.use(url, pineconeApiKey);
        SimilaritySearchHandler.use(url, pineconeApiKey);


        // Step 1: generate embeddings from input string
        var embedding = generateEmbedding("Test");

        // Step 2: save the generated embeddings to a store
        Map<String, String> metadata = new HashMap<>();
        metadata.put("key1", "value1");
        metadata.put("key2", "value2");
        storeEmbedding(embedding, metadata);

        // Step 3: search for the embedding in the store
        var searchEmbeddings = similaritySearch(embedding, 5);

        System.out.println("Embedding generation and storage finished.");
    }
}

