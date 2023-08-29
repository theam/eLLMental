package com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.common;

import java.util.List;
import java.util.Map;

public record Match(
        String id,
        Double score,
        List<Double> values,
        Map<String, String> metadata
) {
}
