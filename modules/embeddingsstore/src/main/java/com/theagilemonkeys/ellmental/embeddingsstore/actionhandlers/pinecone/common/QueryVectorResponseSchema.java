package com.theagilemonkeys.ellmental.embeddingsstore.actionhandlers.pinecone.common;

import java.util.List;

public record QueryVectorResponseSchema(
        String namespace,
        List<Match> matches
) {
}

