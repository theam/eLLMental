package com.theagilemonkeys.ellmental.helloworld;

import com.theagilemonkeys.ellmental.embeddingsgeneration.Embedding;
import com.theagilemonkeys.examplemodule.PrintHelloLibrary;
import com.theagilemonkeys.ellmental.embeddingsgeneration.openai.OpenAIEmbeddingsModel;



public class Main {
    public static void main(String[] args) {
        OpenAIEmbeddingsModel openAI = new OpenAIEmbeddingsModel();
        Embedding embedding =  openAI.generateEmbedding("Test");

        System.out.println(embedding.vector);

        PrintHelloLibrary a = new PrintHelloLibrary();
        a.printHello();
    }
}

