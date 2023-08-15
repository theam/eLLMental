package com.theagilemonkeys.ellmental.embeddingsgeneration.openai;

import com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel;
import com.theagilemonkeys.ellmental.embeddingsgeneration.Embedding;


class OpenAIEmbeddingsModel extends EmbeddingsGenerationModel {
    public Embedding generateEmbedding(String inputString) {
        return new Embedding();
    }
}
