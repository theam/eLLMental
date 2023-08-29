package com.theagilemonkeys.ellmental.embeddingsgeneration;


import com.theagilemonkeys.ellmental.core.schema.Embedding;

/**
 * Abstract class that defines the interface expected by eLLMental for a valid embeddings generation model.
 */
public abstract class EmbeddingsGenerationModel {
    public abstract Embedding generateEmbedding(String text);
}