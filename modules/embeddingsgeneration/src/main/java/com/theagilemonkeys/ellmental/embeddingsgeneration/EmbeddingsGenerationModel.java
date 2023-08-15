package com.theagilemonkeys.ellmental.embeddingsgeneration;


import com.theagilemonkeys.ellmental.core.schema.Embedding;

public abstract class EmbeddingsGenerationModel {
    public abstract Embedding generateEmbedding(String text);
}