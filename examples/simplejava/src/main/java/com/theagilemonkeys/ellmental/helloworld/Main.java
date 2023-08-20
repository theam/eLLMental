package com.theagilemonkeys.ellmental.helloworld;

import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.openai.OpenAIEmbeddingsModel;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;
import com.theagilemonkeys.ellmental.embeddingsstore.pinecone.PineconeEmbeddingsStore;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Step 1: generate embeddings from input string
        OpenAIEmbeddingsModel openAI = new OpenAIEmbeddingsModel();
        Embedding embedding =  openAI.generateEmbedding("Test");
        Map<String, String> metadata = new HashMap<>();
        metadata.put("key1", "value1");
        metadata.put("key2", "value2");

        // Step 2: save the generated embeddings to a store (Pinecone in this case)
        EmbeddingsStore embeddingStore = new PineconeEmbeddingsStore();
        embeddingStore.store(embedding, metadata);

        // Step 3: search for the embedding in the store
        List<Embedding> searchEmbeddings = embeddingStore.similaritySearch(embedding, 5);

        System.out.println("Embedding generation and storage finished.");
    }
}

