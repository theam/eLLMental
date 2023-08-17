package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;


import com.theagilemonkeys.ellmental.core.schema.Embedding;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class UpsertVectorSchema {
    List<Vector> vectors;

    UpsertVectorSchema(Embedding embedding, Map<String,String> metadata ) {
        vectors = Arrays.asList(new Vector(UUID.randomUUID().toString(), embedding.vector, metadata));
    }

}
class SparseValues {
    public List<Integer> indices;
    public List<Integer> values;

    // Getters and setters
}
class  Vector {

    Vector (String id, List<Double> values, Map<String,String> metadata) {
        this.id = id;
        this.values = values;
        this.metadata = metadata;
    }

    public SparseValues sparseValues;
    public Map<String,String> metadata;
    public List<Double> values;
    public String id;
}

// TODO: see about implementing a Metadata class
//class Metadata {
//    Map<String,String> values;
//    Metadata(Map<String,String> values) {
//            this.values = values;
//    }
//}










//class Schema {
//    public static class SparseValues {
//        List<Integer> indices;
//        List<Double> values;
//    }
//
//    public static class Vectors {
//        String id;
//        List<Double> values;
//        SparseValues sparseValues;
//        Map<String, String> metadata;
//    }
//
//    public static class UpsertBody {
//        Vectors vectors;
//        String namespace;
//    }
//
//    public static class QueryBody{
//        Integer topK;
//        String namespace;
//        String filter;
//        Boolean includeValues;
//        Boolean incluedMetadata;
//        List<Double> vector;
//        String id;
//    }
//
//    public static class QueryResponse {
//        List<Match> matches;
//        String namespace;
//        List<String> results;
//    }
//
//    public static class Match{
//        String id;
//        Double score;
//        List<Double> values;
//        SparseValues sparseValues;
//        Map<String, String> metadata;
//    }
//


//}

