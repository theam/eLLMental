package com.theagilemonkeys.webcrawler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class AppConfiguration {

    @Value("${OPEN_AI_API_KEY}")
    private String openAiApiKey;

    @Value("${PINECONE_API_KEY}")
    private String pineconeApiKey;

    @Value("${PINECONE_URL}")
    private String pineconeUrl;

    @Value("${PINECONE_NAMESPACE}")
    private String pineconeNamespace;

    @Bean
    public EmbeddingsSpaceConfiguration embeddingsSpaceComponent() {
        String path = Paths.get("examples","webcrawler", ".env").toString();

        return new EmbeddingsSpaceConfiguration(
                openAiApiKey,
                pineconeApiKey,
                pineconeUrl,
                pineconeNamespace
        );
    }
}


