package com.theagilemonkeys.ellmental.embeddingsmodel;


public abstract class EmbeddingsGenerationModel {
    public abstract Embedding generateEmbedding(String text);
}