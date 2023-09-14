---
slug: /getting-started
---
# Getting started

eLLMental is a library designed for building AI-powered applications written in Java, and it offers production-ready components that can be used right away in your current JVM projects. In this guide, we will showcase how to use the `EmbeddingsSpaceComponent` to find relevant text based on a query.

## Step 1: Add the eLLMental dependencies

You can import the eLLMental library from Maven Central. Below there are some examples of how you can do it.

### Gradle

Incorporate the eLLMental dependencies into your `build.gradle` file.

```groovy
//...
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.theagilemonkeys.ellmental:core:0.0.2"
    implementation "com.theagilemonkeys.ellmental:embeddingsgeneration:0.0.2"
    implementation "com.theagilemonkeys.ellmental:embeddingsstore:0.0.2"
    implementation "com.theagilemonkeys.ellmental:embeddingsspace:0.0.2"
    // Other dependencies
}
//...
```

### Maven

You can also add the eLLMental dependencies into your `pom.xml` file.

```xml
<project>
    <!-- ... other configurations ... -->

    <!-- Add the eLLMental dependencies -->
    <dependencies>
        <dependency>
            <groupId>com.theagilemonkeys.ellmental</groupId>
            <artifactId>core</artifactId>
            <version>0.0.2</version>
        </dependency>
        <dependency>
            <groupId>com.theagilemonkeys.ellmental</groupId>
            <artifactId>embeddingsgeneration</artifactId>
            <version>0.0.2</version>
        </dependency>
        <dependency>
            <groupId>com.theagilemonkeys.ellmental</groupId>
            <artifactId>embeddingsstore</artifactId>
            <version>0.0.2</version>
        </dependency>
        <dependency>
            <groupId>com.theagilemonkeys.ellmental</groupId>
            <artifactId>embeddingsspace</artifactId>
            <version>0.0.2</version>
        </dependency>
    </dependencies>

    <!-- ... other configurations ... -->
</project>

```

## Step 2: Initializing the EmbeddingsSpaceComponent

Before initializing the `EmbeddingsSpaceComponent`, set up the `OpenAIEmbeddingsModel` and `PineconeVectorStore`.

Retrieve the required API tokens and configuration parameters following the [PineCone quickstart guide](https://docs.pinecone.io/docs/quickstart) and [OpenAI API keys guide](https://platform.openai.com/docs/guides/production-best-practices/api-keys).

```java
import com.theagilemonkeys.ellmental.embeddingsmodel.openai.OpenAIEmbeddingsModel;
import com.theagilemonkeys.ellmental.vectorstore.pinecone.PineconeVectorStore;

public OpenAIEmbeddingsModel embeddingsModel() {
    return new OpenAIEmbeddingsModel("OPEN_AI_API_KEY");
}

public PineconeVectorStore vectorStore() {
    return new PineconeVectorStore("PINECONE_API_KEY", "PINECONE_URL", "PINECONE_NAMESPACE");
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
        
        // Add some embedding samples to the embeddings space.
        embeddingsSpace.save("Hello, eLLMental!");
        embeddingsSpace.save("Hello, world!");
        embeddingsSpace.save("Hi!");
        embeddingsSpace.save("Cats are cute");
        embeddingsSpace.save("Dogs are loyal");
        // You can provide Metadata to the `save` call too
        Map<String, String> metadata = new HashMap<>();
        metadata.put("key", "value");
        embeddingsSpace.save("Hey there!", metadata)

        
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

## eLLMental :heart: Springboot

If you prefer to use eLLMental from Springboot, you can always use the `application.properties` file to import your environment variables and just modify a little bit the code as seen below:

### Importing env variables from application.properties

```
# application.properties

OPEN_AI_API_KEY=<your_openai_key>
PINECONE_API_KEY=<your_pinecone_key>
PINECONE_URL=<your_pinecone_url>
PINECONE_NAMESPACE=<your_pinecone_namespace>
```

### Configuring EmbeddingsSpaceComponent

```java
import com.theagilemonkeys.ellmental.EmbeddingsSpaceComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EllmentalConfiguration{

    @Value("${OPEN_AI_API_KEY}")
    private String openAiApiKey;

    @Value("${PINECONE_API_KEY}")
    private String pineconeApiKey;

    @Value("${PINECONE_URL}")
    private String pineconeUrl;

    @Value("${PINECONE_NAMESPACE}")
    private String pineconeNamespace;

    private OpenAIEmbeddingsModel embeddingsModel() {
        return new OpenAIEmbeddingsModel(openAiApiKey);
    }

    private PineconeVectorStore vectorStore() {
        return new PineconeVectorStore(pineconeApiKey, pineconeUrl, pineconeNamespace);
    }

    // Usable public Bean
    @Bean
    public EmbeddingsSpaceComponent embeddingsSpaceComponent() {
        return new EmbeddingsSpaceComponent(embeddingsModel(), vectorStore());
    }
}
```

### Autowiring EmbeddingsSpaceComponent

```java
import com.theagilemonkeys.ellmental.EmbeddingsSpaceComponent;
import org.springframework.beans.factory.annotation.Autowired;

public class SomeServiceClass {
    private final EmbeddingsSpaceComponent embeddingsSpaceComponent;

    @Autowired
    public SomeServiceClass(EmbeddingsSpaceComponent embeddingsSpaceComponent){
        this.embeddingsSpaceComponent = embeddingsSpaceComponent;
    }

    // ...Here you can use embeddingsSpaceComponent
}
```


## Next steps

Now that you've learned the basics, you can include eLLMental in your own projects and start experimenting. Try to generate embeddings for a larger corpus of texts like HTML files extracted from a web scraper process, a series of blog posts from your database or a collection of tweets from your Twitter account
