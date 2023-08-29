package com.theagilemonkeys.ellmental.embeddingsstore;

import java.util.List;
import java.util.Map;

import com.theagilemonkeys.ellmental.core.schema.Embedding;

/**
 * Abstract class that defines the expected interface for a persistence mechanism capable of storing and querying embeddings.
 */
public abstract class EmbeddingsStore {
    //TODO: check if there is an issue using a map instead of a metadata class
    public abstract void store(Embedding embedding);
    public abstract List<Embedding> similaritySearch(Embedding reference, int limit);
}