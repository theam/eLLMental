package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;

import java.util.List;

public class QueryVectorRequestSchema {

    QueryVectorRequestSchema(
        Integer topK,
        Boolean includeValues,
        Boolean includeMetadata,
        List<Double> vector,
        String namespace
    ) {
        this.topK = topK;
        this.includeValues = includeValues;
        this.includeMetadata = includeMetadata;
        this.vector = vector;
        this.namespace = namespace;

    }
    public Integer topK;
    public Boolean includeValues;
    public Boolean includeMetadata;
    public List<Double> vector;
    public String namespace;



}
