package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class QueryVectorRequestSchema {
    public Integer topK;
    public Boolean includeValues;
    public Boolean includeMetadata;
    public List<Double> vector;
    public String namespace;
}
