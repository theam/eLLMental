package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class QueryVectorResponseSchema {
    String namespace;
    List<Match> matches;
}

class Match {
    UUID id;
    Double score;
    List<Double> values;
    Map<String,String> metadata;
}