package com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.common;


import com.theagilemonkeys.ellmental.core.schema.Embedding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public record UpsertVectorSchema(
        List<Vector> vectors
) {
    public static UpsertVectorSchema of(Embedding embedding, Map<String, String> metadata) {
        var vectors = new ArrayList<Vector>();
        vectors.add(new Vector(UUID.randomUUID().toString(), embedding.vector, metadata, null));
        return new UpsertVectorSchema(vectors);
    }
}

record SparseValues(
        List<Integer> indices,
        List<Integer> values
) {
}

record Vector(
        String id,
        List<Double> values,
        Map<String, String> metadata,
        SparseValues sparseValues
) {
}
