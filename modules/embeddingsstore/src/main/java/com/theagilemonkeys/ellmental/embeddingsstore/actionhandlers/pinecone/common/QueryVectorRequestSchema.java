package com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.common;

import java.util.List;

public record QueryVectorRequestSchema(
        Integer topK,
        Boolean includeValues,
        Boolean includeMetadata,
        List<Double> vector,
        String namespace
) {
}
