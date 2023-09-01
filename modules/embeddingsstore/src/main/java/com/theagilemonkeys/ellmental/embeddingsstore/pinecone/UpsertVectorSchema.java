package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;


import com.theagilemonkeys.ellmental.core.schema.Embedding;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.ArrayList;

@Getter
@ToString
@EqualsAndHashCode
public class UpsertVectorSchema {
    private final List<Vector> vectors;
    private final String namespace;

    public UpsertVectorSchema(Embedding embedding, String namespace) {
        this.vectors = new ArrayList<>();
        this.vectors.add(new Vector(embedding.id, embedding.vector, embedding.metadata));
        this.namespace = namespace;
    }

}
