package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;


import com.theagilemonkeys.ellmental.core.schema.Embedding;

import java.util.List;
import java.util.ArrayList;


public class UpsertVectorSchema {
    List<Vector> vectors;

    UpsertVectorSchema(Embedding embedding) {
        vectors = new ArrayList<>();
        vectors.add(new Vector(embedding.id(), embedding.vector(), embedding.metadata()));
    }

}
