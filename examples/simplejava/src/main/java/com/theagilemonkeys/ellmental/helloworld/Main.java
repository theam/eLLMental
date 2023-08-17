package com.theagilemonkeys.ellmental.helloworld;

import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.examplemodule.PrintHelloLibrary;
import com.theagilemonkeys.ellmental.embeddingsgeneration.openai.OpenAIEmbeddingsModel;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;
import com.theagilemonkeys.ellmental.embeddingsstore.pinecone.PineconeEmbeddingsStore;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        OpenAIEmbeddingsModel openAI = new OpenAIEmbeddingsModel();
        Embedding embedding =  openAI.generateEmbedding("Test");


        EmbeddingsStore embeddingStore = new PineconeEmbeddingsStore();

        Map<String, String> metadata = new HashMap<>();
        metadata.put("key1", "value1");
        metadata.put("key2", "value2");


        embeddingStore.store(embedding, metadata);

        PrintHelloLibrary a = new PrintHelloLibrary();
        a.printHello();
    }
}

