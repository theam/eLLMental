package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;


import com.theagilemonkeys.ellmental.core.schema.Embedding;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.UUID;


public class UpsertVectorSchema {
    List<Vector> vectors;

    UpsertVectorSchema(Embedding embedding) {
        vectors = new ArrayList<>();
        vectors.add(new Vector(embedding.id(), embedding.vector(), embedding.metadata()));
    }

}
class SparseValues {
    public List<Integer> indices;
    public List<Integer> values;
}
class  Vector {

    Vector (UUID id, List<Double> values, Map<String,String> metadata) {
        this.id = id;
        this.values = values;
        this.metadata = metadata;
    }

    public SparseValues sparseValues;
    public Map<String,String> metadata;
    public List<Double> values;
    public UUID id;
}
