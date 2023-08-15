package com.theagilemonkeys.ellmental.embeddingsgeneration;


public abstract class EmbeddingsGenerationModel {
    public abstract Embedding generateEmbedding(String text);
}