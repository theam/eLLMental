package com.theagilemonkeys.ellmental.core.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Embeddings represent a point in the embeddings space, representing the semantics of a given text.
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Embedding {
    public final UUID id;
    public final List<Double> vector;
    public final Map<String, String> metadata;
    public Double score;
}
