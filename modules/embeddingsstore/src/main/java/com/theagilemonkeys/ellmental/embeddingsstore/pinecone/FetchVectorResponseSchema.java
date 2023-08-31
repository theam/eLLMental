package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;

import lombok.Data;

import java.util.List;

@Data
public class FetchVectorResponseSchema {
    private final List<Vector> vectors;
    private final String namespace;
}
