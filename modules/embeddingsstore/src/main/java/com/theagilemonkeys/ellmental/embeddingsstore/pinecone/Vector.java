package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;


import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class Vector {

    private SparseValues sparseValues;
    private final Map<String,String> metadata;
    private final List<Double> values;
    private final UUID id;
}
