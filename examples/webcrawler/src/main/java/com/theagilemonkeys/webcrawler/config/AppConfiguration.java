package com.theagilemonkeys.webcrawler.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class AppConfiguration {
    @Bean
    public EmbeddingsSpaceConfiguration embeddingsSpaceComponent() {
        String path = Paths.get("examples","webcrawler", ".env").toString();
        Dotenv dotenv = Dotenv.configure()
                .directory(path)
                .filename(".env")
                .load();
        return new EmbeddingsSpaceConfiguration(
                 dotenv.get("OPEN_AI_API_KEY"),
                dotenv.get("PINECONE_API_KEY"),
                dotenv.get("PINECONE_URL"),
                dotenv.get("PINECONE_NAMESPACE")
        );
    }
}


