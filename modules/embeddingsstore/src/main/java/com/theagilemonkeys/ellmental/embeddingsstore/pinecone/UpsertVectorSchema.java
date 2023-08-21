package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;


import com.theagilemonkeys.ellmental.core.schema.Embedding;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;


public class UpsertVectorSchema {
    List<Vector> vectors;

    UpsertVectorSchema(Embedding embedding, Map<String,String> metadata ) {
        vectors = new ArrayList<>();
        vectors.add(new Vector(UUID.randomUUID().toString(), embedding.vector, metadata));
    }

}
class SparseValues {
    public List<Integer> indices;
    public List<Integer> values;
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
