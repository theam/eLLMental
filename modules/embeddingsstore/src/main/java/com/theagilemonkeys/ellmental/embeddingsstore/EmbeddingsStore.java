package com.theagilemonkeys.ellmental.embeddingsstore;

import java.util.List;

public abstract class EmbeddingsStore {
    public abstract void store(Embedding embedding, Metadata metadata);
    public abstract List<Embedding> similaritySearch(Embedding reference, int limit);
}