package com.theagilemonkeys.ellmental.core.schema;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Embeddings represent a point in the embeddings space, representing the semantics of a given text.
 */
public record Embedding(
        UUID id,
        List<Double> vector,
        Map<String, String> metadata
) {}
