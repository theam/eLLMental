package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;

import com.theagilemonkeys.ellmental.embeddingsstore.Embedding;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;
import com.theagilemonkeys.ellmental.embeddingsstore.Metadata;

import java.util.Arrays;
import java.util.List;

public class PineconeEmbeddingsStore extends EmbeddingsStore {
    public void store(Embedding embedding, Metadata metadata) {

    }
    public List<Embedding> similaritySearch(Embedding reference, int limit) {
        return Arrays.asList(new Embedding(), new Embedding());
    }
}
