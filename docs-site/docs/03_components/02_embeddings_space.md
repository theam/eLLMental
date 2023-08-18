# `EmbeddingsSpaceComponent`

## Introduction

The `EmbeddingsSpaceComponent` represents an embeddings space, facilitating the management and operations within it. Think of embeddings as a numeric representation of the meaning behind the text. Similar to how coordinates help pinpoint locations on Earth, in the embeddings space, semantically similar concepts cluster closer together. Before diving in, make sure to follow the [Getting Started Guide](../02_getting_started.md) to install the library in your project. It'd also be advisable to familiarize yourself with the [core abstractions](01_core_abstractions.md).

## Overview

Leveraging the power of embeddings models, this component allows you to represent pieces of text in an embeddings space. Once you generate an embedding, it's stored using an embeddings database, along with its metadata for efficient retrieval.

[//]: # (TODO: Highlight this as a warning message in Docusaurus)
> **Warning**: The current version supports the [OpenAI embeddings model](https://platform.openai.com/docs/guides/embeddings) and [Pinecone](https://www.pinecone.io) for storage. Please provide the necessary credentials for these services. Note: these services may involve costs, always review their pricing details before use.

## `Embedding` object

In eLLMental embeddings are represented by the `Embedding` class, which has the following attributes:

- `id`: The unique identifier of the embedding.
- `embedding`: The numeric representation of the text.
- `metadata`: Additional information associated with the embedding.
- `createdAt`: The timestamp of the embedding creation.
- `modelId`: The identifier of the embeddings model used to generate the embedding.

```java
public class Embedding {
    private final String id;
    private final float[] embedding;
    private final Map<String, String> metadata;
    private final Instant createdAt;
    private final String modelId;
}
```

The `EmbeddingsSpaceComponent` interface defines the following methods:

## Constructor

To instantiate an `EmbeddingsSpaceComponent`, provide both an [`EmbeddingsGenerationModel`](01_core_abstractions.md#embeddingsgenerationmodel) and an [`EmbeddingsStore`](01_core_abstractions.md#embeddingsstore).

```java
EmbeddingsGenerationModel openAIModel = new OpenAIEmbeddingsGenerationModel("YOUR_OPENAI_API_KEY");
EmbeddingsStore pineconeStore = new PineconeEmbeddingsStore("YOUR_PINECONE_URL", "YOUR_PINECONE_API_KEY", "YOUR_PINECONE_SPACE");
EmbeddingsSpaceComponent embeddingsSpace = new EmbeddingsSpaceComponent(openAIModel, pineconeStore);
```

## `generate`

Generates an embedding from a text without persisting it.

- **Parameters**:
    - `text`: The textual input for embedding.
    - `additionalMetadata`: Supplementary metadata associated with the text.
- **Returns**: The generated embedding.

```java
String sampleText = "Hello, eLLMental!";
Map<String, String> additionalMetadata = new HashMap<>();
additionalMetadata.put("key", "value");

Embedding embedding = embeddingsSpace.generate(sampleText, additionalMetadata);
```

## `save`

Generates and persists an embedding for a given text.

- **Parameters**:
    - `text`: Text to be embedded.
    - `additionalMetadata`: (Optional) Additional metadata.
- **Returns**: The generated embedding.

```java
Map<String, String> additionalMetadata = new HashMap<>();
additionalMetadata.put("key", "value");

String sampleText = "Hello, eLLMental!";
Embedding embedding = embeddingsSpace.save(sampleText, additionalMetadata);

// Or just
Embedding embedding = embeddingSpace.save(sampleText);
```

## `mostSimilarEmbeddings`

Fetches embeddings semantically closest to a reference text or embedding.

With a reference text:

- **Parameters**:
    - `referenceText`: The text for comparison.
    - `limit`: Maximum number of similar embeddings to return.

```java
// First we add a few embeddings to the space
embeddingsSpace.save("Hello, eLLMental!");
embeddingsSpace.save("Hello, world!");

List<Embedding> closestNeighbors = embeddingsSpace.mostSimilarEmbeddings("Greetings!", 3);
// closestNeighbors will contain the embeddings for "Hello, eLLMental!" and "Hello, world!"
```

With a reference embedding:

- **Parameters**:
    - `referenceEmbedding`: The embedding for comparison.
    - `limit`: Maximum number of similar embeddings to return.

```java
// First we add a few embeddings to the space
embeddingsSpace.add("Hello, eLLMental!");
embeddingsSpace.add("Hello, world!");
        
Embedding embedding = embeddingsSpace.generate("Hello everyone!");

List<Embedding> closestNeighbors = embeddingsSpace.mostSimilarEmbeddings(embedding, 3);
// closestNeighbors will contain the embeddings for "Hello, eLLMental!" and "Hello, world!"
```

## `calculateRelationshipVector`

Computes a relationship vector for provided text pairs (To be used with the `translateEmbedding` method)

- **Parameters**:
    - `textPairs`: Array of text pairs.

For instance, with the following list of text pairs:

| Text 1 | Text 2 |
|--------|--------|
| Man    | Woman  |
| Boy    | Girl   |
| King   | Queen  |
| Prince | Princess |
| Father | Mother |

The relationship vector for this group represents a translation in the embeddings space that, given a word that matches the ones in the left column, provides the location of a word that would likely appear in the right column for the given word. See the documentation for [`translateEmbedding`](#translateEmbedding) for more details.

The `RelationshipVector` class is defined as follows:

```java
public class RelationshipVector {
    public final String label;
    public final float[] vector;
}
```

And it can be calculated like this:

```java
String[][] textPairs = [["Man", "Woman"], ["Boy", "Girl"], ["King", "Queen"], ["Prince", "Princess"], ["Father", "Mother"]];
RelationshipVector relationshipVector = embeddingsSpace.calculateRelationshipVector(textPairs);
```

## `storeNamedRelationshipVector`

Stores a relationship vector in the embeddings store and assigns it a label for later use.

- **Parameters**:
    - `label`: The label to assign to the relationship vector.
    - `relationshipVector`: The relationship vector to store.
    

```java
// First we calculate a relationship vector
RelationshipVector relationshipVector = embeddingsSpace.calculateRelationshipVector(textPairs);

// Then we store it in the embeddings store for future use
embeddingsSpace.storeNamedRelationshipVector("feminize", relationshipVector);
```

## `translateEmbedding`

Shifts a reference text embedding in the embeddings space to find the location of the text that would meet the relationship represented by the vector.

- **Parameters**:
    - `referenceText`: The primary embedding.
    - `vector`: The vector determining translation.

This is useful if you want to search for embeddings that are similar to a given one, but in a different context. For instance, let's say we have the following embedding:

And a relationship vector calculated with the `calculateRelationshipVector` method as follows:

```java
String[][] textPairs = [["Man", "Woman"], ["Boy", "Girl"], ["King", "Queen"], ["Prince", "Princess"], ["Father", "Mother"]];
RelationshipVector relationshipVector = embeddingsSpace.calculateRelationshipVector(textPairs);
```

We can use the relationship vector to find the location of the words that would be similar to "Cow" instead of "Bull". Notice that embeddings cannot be reversed, and we can't really know if this embedding represents a cow, but it will give us a good approximation that can be used to refine search results later.

```java
// This will create an estimated embedding of the word "Cow"
Embedding likelyACowEmbedding = embeddingsSpace.translateEmbedding("Bull", relationshipVector);

// We use it as any other embedding to find stored texts that are similar to "Cow"
List<Embedding> similarToCowEmbeddings = embeddingsSpace.mostSimilarEmbeddings(likelyACowEmbedding, 5);
```

## `get`

Retrieves an embedding from the embeddings store using its ID.

- **Parameters**:
    - `id`: The ID of the embedding to retrieve.
- **Returns**: The desired embedding or `null` if not found.

```java
Embedding embedding = embeddingsSpace.get("embedding-id");
```

## `delete`

Deletes an embedding from the embeddings store using its ID.

- **Parameters**:
    - `id`: The ID of the embedding to delete.

```java
embeddingsSpace.delete("embedding-id");
```