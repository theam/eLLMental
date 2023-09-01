package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class FetchVectorSchema {
    private final List<UUID> ids;
    private String namespace;
}
