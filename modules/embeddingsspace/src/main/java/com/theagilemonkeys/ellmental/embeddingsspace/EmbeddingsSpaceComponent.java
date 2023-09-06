package com.theagilemonkeys.ellmental.embeddingsspace;

import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel;
import com.theagilemonkeys.ellmental.embeddingsgeneration.openai.OpenAIEmbeddingsModel;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class EmbeddingsSpaceComponent {
    private static final Logger log = LoggerFactory.getLogger(EmbeddingsSpaceComponent.class);

    private final EmbeddingsGenerationModel embeddingsGenerationModel;
    private final EmbeddingsStore embeddingsStore;

    private Embedding generate(String text) {
        log.debug("Generating embedding for text {}", text);
        return embeddingsGenerationModel.generateEmbedding(text);
    }

    public Embedding save(String text) {
        Embedding embedding = generate(text);
        log.debug("Saving embedding {} to embedding store", embedding);
        embeddingsStore.store(embedding);
        return embedding;
    }

    public List<Embedding> mostSimilarEmbeddings(Embedding referenceEmbedding, int limit) {
        log.debug("Getting most similar embeddings for embedding {}. Limit {}", referenceEmbedding, limit);
        return embeddingsStore.similaritySearch(referenceEmbedding, limit);
    }

    public List<Embedding> mostSimilarEmbeddings(String referenceText, int limit) {
        Embedding embedding = generate(referenceText);
        log.debug("Getting most similar embeddings for text {}. Limit {}", referenceText, limit);
        return mostSimilarEmbeddings(embedding, limit);
    }

    public Embedding get(UUID uuid) {
        log.debug("Retrieving embedding from uuid {}", uuid);
        return embeddingsStore.get(uuid);
    }

    public void delete(UUID uuid) {
        log.debug("Deleting embedding for uuid {}", uuid);
        embeddingsStore.delete(uuid);
    }
}