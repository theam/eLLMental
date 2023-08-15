package com.theagilemonkeys.ellmental.embeddingsmodel.openai;

import com.theagilemonkeys.ellmental.embeddingsmodel.EmbeddingsGenerationModel;
import com.theagilemonkeys.ellmental.embeddingsmodel.Embedding;


class OpenAIEmbeddingsModel extends EmbeddingsGenerationModel {
    public Embedding generateEmbedding(String inputString) {
        return new Embedding();
    }
}
