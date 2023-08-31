package com.theagilemonkeys.ellmental.embeddingsspace;

import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class EmbeddingsSpaceComponent {

    private final EmbeddingsGenerationModel embeddingsGenerationModel;
    private final EmbeddingsStore embeddingsStore;

    private Embedding generate(String text) {
        return embeddingsGenerationModel.generateEmbedding(text);
    }

    public Embedding save(String text) {
        Embedding embedding = generate(text);
        embeddingsStore.store(embedding);
        return embedding;
    }

    public List<Embedding> mostSimilarEmbeddings(Embedding referenceEmbedding, int limit) {
        return embeddingsStore.similaritySearch(referenceEmbedding, limit);
    }

    public List<Embedding> mostSimilarEmbeddings(String referenceText, int limit) {
        Embedding embedding = generate(referenceText);

        return mostSimilarEmbeddings(embedding, limit);
    }

    public Embedding get(UUID uuid) {
        return embeddingsStore.get(uuid);
    }

    public void delete(UUID uuid) {
        embeddingsStore.delete(uuid);
    }
}