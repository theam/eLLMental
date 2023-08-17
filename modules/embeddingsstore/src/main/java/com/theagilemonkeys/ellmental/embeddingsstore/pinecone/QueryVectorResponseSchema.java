package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;

import java.util.List;
import java.util.Map;

public class QueryVectorResponseSchema {
    String namespace;
    List<Match> matches;
}

class Match {
    String id;
    Integer score;
    List<Double> values;
    Map<String,String> metadata;
}