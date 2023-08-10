# Core Abstractions

eLLMental uses different 3rd party components and APIs and provides a unified interface. To ensure extensibility and avoid tight coupling with any specific API, the library provides a series of abstract classes that define the expected interface for these components work with eLLMental. To use eLLMental, you can provide your own implementation or use one of the built-in concrete implementations.  

## `EmbeddingsGenerationModel`

This abstract class defines the interface expected by eLLMental for a valid embeddings generation model.

```java
public abstract class EmbeddingsGenerationModel {
    public abstract Embedding generateEmbedding(String text);
}
```

### `OpenAIEmbeddingsGenerationModel`

eLLMental provides an implementation to use [OpenAI's embeddings model](https://platform.openai.com/docs/guides/embeddings). This model is only accessible via API, so you'll need to initialize it with a valid OpenAI API key.

```java
EmbeddingsGenerationModel openAIModel = new OpenAIEmbeddingsGenerationModel("YOUR_OPENAI_API_KEY");

// You'll rarely need to interact directly with the `openAIModel`, but you can use it to generate an embedding:
openAIModel.generateEmbedding("Sample string");
```

## `EmbeddingsStore`

This abstract class defines the expected interface for a persistence mechanism capable of storing and querying embeddings:

```java
public abstract class EmbeddingsStore {
    public abstract void store(Embedding embedding, Metadata metadata);
    public abstract List<Embedding> similaritySearch(Embedding reference, int limit);
}
```

### `PineconeEmbeddingsStore`

eLLMental provides a concrete implementation for Pinecone, which requires defining an URL, an API Key and a space.

```java
EmbeddingsStore pineconeStore = new PineconeEmbeddingsStore("YOUR_PINECONE_URL", "YOUR_PINECONE_API_KEY", "YOUR_PINECONE_SPACE");

// You can insert or perform simularity searches using this object
pineconeStore.store(someEmbedding, someMetadata);
List<Embedding> similarEmbeddings = pineconeStore.similaritySearch(referenceEmbedding, 5);
```