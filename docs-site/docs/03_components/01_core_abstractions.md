# Core Abstractions

eLLMental uses different 3rd party components and APIs and provides a unified interface. To ensure extensibility and avoid tight coupling with any specific API, the library provides a series of abstract classes that define the expected interface for these components to work with eLLMental. To use eLLMental, you can provide your own implementation or use one of the built-in concrete implementations.

## `Embedding` object

In eLLMental embeddings are represented by the `Embedding` record, which has the following attributes:

- `id`: An unique identifier of the embedding.
- `vector`: A numeric vector that represents the semantic location of the text.
- `metadata`: Additional information associated with the embedding. It can be used to store the original text, the model used to generate the embedding, or any other information you may find useful.

```java
public record Embedding(
        UUID id,
        List<Double> vector,
        Map<String, String> metadata
) {}

```

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

// You'll rarely need to interact directly with the `openAIModel`, but you can use it to generate an embedding object:
Embedding embedding = openAIModel.generateEmbedding("Sample string");
```

The OpenAI embeddings generator will automatically include the original text, the timestamp and the model used to generate the embedding in the metadata. 

## `EmbeddingsStore`

This abstract class defines the expected interface for a persistence mechanism capable of storing and querying embeddings:

```java
public abstract class EmbeddingsStore {
    public abstract void store(Embedding embedding);
    public abstract List<Embedding> similaritySearch(Embedding reference, int limit);
}
```

### `PineconeEmbeddingsStore`

eLLMental provides a concrete implementation for Pinecone, which requires defining a URL, an API Key and a space.

```java
EmbeddingsStore pineconeStore = new PineconeEmbeddingsStore("YOUR_PINECONE_URL", "YOUR_PINECONE_API_KEY", "YOUR_PINECONE_NAMESPACE");

// You can now insert, fetch, delete or perform similarity searches using the pineconeStore instance:
pineconeStore.store(someEmbedding);
List<Embedding> similarEmbeddings = pineconeStore.similaritySearch(referenceEmbedding, 5);
Embedding embedding = pineconeStore.get("my-uuid");
Embedding anotherEmbedding = pineconeStore.get("my-uuid", "my-namespace");
pineconeStore.delete("my-uuid");
```