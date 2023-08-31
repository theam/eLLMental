package com.theagilemonkeys.ellmental.embeddingsstore.pinecone;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class DeleteVectorSchema {
    private final List<UUID> ids;
    private boolean deleteAll;
    private String namespace;
    private Map<String, String> filterMetadata;
}
