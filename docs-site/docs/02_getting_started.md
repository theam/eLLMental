---
slug: /getting-started
---
# Getting started

eLLMental is a library designed for building AI-powered applications written in Java, and it offers production-ready components that can be used right away in your current JVM projects. In this guide, we will showcase how to use the `EmbeddingsSpaceComponent` to find relevant text based on a query.

## Step 1: Add the eLLMental dependencies using [JitPack](https://jitpack.io)

Incorporate the eLLMental dependencies into your `build.gradle` file.

Here's a reference for you:

```java
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.theam:ellmental:main'
}
```

## Step 2: Initializing the EmbeddingsSpaceComponent

Before initializing the `EmbeddingsSpaceComponent`, set up the `OpenAIEmbeddingsModel` and `PineconeVectorStore`.

Retrieve the required API tokens and configuration parameters following the [PineCone quickstart guide](https://docs.pinecone.io/docs/quickstart) and [OpenAI API keys guide](https://platform.openai.com/docs/guides/production-best-practices/api-keys).

```java
import com.theagilemonkeys.ellmental.embeddingsmodel.openai.OpenAIEmbeddingsModel;
import com.theagilemonkeys.ellmental.vectorstore.pinecone.PineconeVectorStore;

public OpenAIEmbeddingsModel embeddingsModel() {
    String openaiToken = System.getenv("OPEN_AI_API_KEY");
    if (openaiToken == null) {
        throw new RuntimeException("OPEN_AI_API_KEY environment variable is not set");
    }
    return new OpenAIEmbeddingsModel(openAIToken);
}

public PineconeVectorStore vectorStore() {
    String pineconeToken = System.getenv("PINECONE_API_KEY");
    if (pineconeToken == null) {
        throw new RuntimeException("PINECONE_API_KEY environment variable is not set");
    }
    String pineconeUrl = System.getenv("PINECONE_URL");
    if (pineconeUrl == null) {
        throw new RuntimeException("PINECONE_URL environment variable is not set");
    }
    String pineconeNamespace = System.getenv("PINECONE_NAMESPACE");  // optional parameter
    return new PineconeVectorStore(pineconeToken, pineconeUrl, pineconeNamespace);
}
```

Now, initialize the `EmbeddingsSpaceComponent`:

```java
import com.theagilemonkeys.ellmental.EmbeddingsSpaceComponent;

public EmbeddingsSpaceComponent initializeEmbeddingsSpace() {
    return new EmbeddingsSpaceComponent(embeddingsModel(), vectorStore());
}
```

## Step 3: Running the example

To run an example, you can write a simple main function:

```java
public class MainApp {

    public static void main(String[] args) {
        EmbeddingsSpaceComponent embeddingsSpace = initializeEmbeddingsSpace();
        
        // Add some embedding samples to the embeddings space
        embeddingsSpace.add("Hello, eLLMental!", null);
        embeddingsSpace.add("Hello, world!", null);
        embeddingsSpace.add("Hi!", null);
        embeddingsSpace.add("Cats are cute", null);
        embeddingsSpace.add("Dogs are loyal", null);
        
        // Search similar embeddings
        List<Embedding> results = embeddingsSpace.mostSimilarEmbeddings("Greetings!", 3);
        for (Embedding embedding : results) {
            System.out.println(embedding.getText());
        }
    }
}
```

Run the main function, and you should see the most similar texts to "Greetings!":

```bash
$ ./gradlew run

> Task :run
Hello, eLLMental!
Hello, world!
Hi!
```

> Notice that the result outputs three entries because we specified the limit to be 3 in the `mostSimilarEmbeddings` function, but you can change this value to any number you want. Take into account that in the embeddings space, the database will calculate distances with every other embedding, so higher limits may return results that are not strictly similar to the query. Take into account that the list is ranked by similarity, so the first result is the most similar to the query and the latest is the least similar.

## Next steps

Now that you've learned the basics, you can inlcude eLLMental in your own projects and start experimenting. Try to generate embeddings for a larger corpus of texts like HTML files extracted from a web scraper process, a series of blog posts from your database or a collection of tweets from your Twitter account
