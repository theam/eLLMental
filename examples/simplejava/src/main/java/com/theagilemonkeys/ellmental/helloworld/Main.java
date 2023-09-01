package com.theagilemonkeys.ellmental.helloworld;

import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel;
import com.theagilemonkeys.ellmental.embeddingsgeneration.openai.OpenAIEmbeddingsModel;
import com.theagilemonkeys.ellmental.embeddingsspace.EmbeddingsSpaceComponent;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;
import com.theagilemonkeys.ellmental.embeddingsstore.pinecone.PineconeEmbeddingsStore;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmbeddingsGenerationModel generationModel = new OpenAIEmbeddingsModel("api_key");
        EmbeddingsStore embeddingsStore = new PineconeEmbeddingsStore("https://<index>.svc.<environment>.pinecone.io", "api_key");
        EmbeddingsSpaceComponent embeddingsSpaceComponent = new EmbeddingsSpaceComponent(generationModel, embeddingsStore);

        Embedding embedding = embeddingsSpaceComponent.save("Hello World!");
        System.out.printf("Saved embedding: %s", embedding);

        System.out.printf("Got embedding: %s", embeddingsSpaceComponent.get(embedding.id));

        embeddingsSpaceComponent.delete(embedding.id);

        System.out.printf("Got embedding after delete: %s", embeddingsSpaceComponent.get(embedding.id));
    }
}

