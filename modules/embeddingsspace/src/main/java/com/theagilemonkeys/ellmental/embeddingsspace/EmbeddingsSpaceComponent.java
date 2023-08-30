package com.theagilemonkeys.ellmental.embeddingsspace;

import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmbeddingsSpaceComponent {

    private final EmbeddingsGenerationModel model;
    private final EmbeddingsStore store;

    public EmbeddingsSpaceComponent(EmbeddingsGenerationModel model, EmbeddingsStore store) {
        this.model = model;
        this.store = store;
    }

    public Embedding generate(String text) {
        Embedding embedding = null;
        try {
            embedding = model.generateEmbedding(text);
        } catch (RuntimeException e) {
            // TODO: Create and catch EmbeddingGenerationException?
            System.out.printf("Error while generating embedding tor text %s", text);
        }
        return embedding;
    }

    public Embedding generate(String text, Map<String, String> additionalMetadata) {
        // TODO: What about metadata?
        return model.generateEmbedding(text);
    }

    public Embedding save(String text) {
        Embedding embedding = generate(text);

        try {
            store.store(embedding);
            return embedding;
        } catch (RuntimeException e) {
            // TODO: Create and catch EmbeddingStoreException?
            System.out.printf("Error while storing embedding %s into the embedding space%n", embedding.toString());
            return null;
        }
    }

    public List<Embedding> mostSimilarEmbeddings(Embedding referenceEmbedding, int limit) {
        List<Embedding> embeddings = new ArrayList<>();

        try {
            embeddings = store.similaritySearch(referenceEmbedding, limit);
        } catch (RuntimeException e) {
            // TODO: Create and catch SimilaritySearchException?
            System.out.printf("Error while looking for most similar embeddings for referenceText %s", referenceText);
        }

        return embeddings;
    }

    public List<Embedding> mostSimilarEmbeddings(String referenceText, int limit) {
        Embedding embedding = generate(referenceText);

        return mostSimilarEmbeddings(embedding, limit);
    }

}