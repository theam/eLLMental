package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;


import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class Vector {

    private final UUID id;
    private final List<Double> values;
    private final Map<String,String> metadata;
    private SparseValues sparseValues;
}
