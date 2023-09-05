package com.theagilemonkeys.webcrawler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public record EmbeddingsSpaceConfiguration(
        String openaiKey,
        String pineconeKey,
        String pineconeUrl,
        String pineconeNamespace
) {
}
