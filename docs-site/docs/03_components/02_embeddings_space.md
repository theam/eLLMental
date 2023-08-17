# `EmbeddingsSpaceComponent`

## Introduction

The `EmbeddingsSpaceComponent` represents an embeddings space, facilitating the management and operations within it. Think of embeddings as a numeric representation of the meaning behind the text. Similar to how coordinates help pinpoint locations on Earth, in the embeddings space, semantically similar concepts cluster closer together. Before diving in, make sure to follow the [Getting Started Guide](../02_getting_started.md) to install the library in your project. It'd also be advisable to familiarize yourself with the [core abstractions](01_core_abstractions.md).

## Overview

Leveraging the power of embeddings models, this component allows you to represent pieces of text in an embeddings space. Once you generate an embedding, it's stored using an embeddings database, along with its metadata for efficient retrieval.

[//]: # (TODO: Highlight this as a warning message in Docusaurus)
> **Warning**: The current version supports the [OpenAI embeddings model](https://platform.openai.com/docs/guides/embeddings) and [Pinecone](https://www.pinecone.io) for storage. Please provide the necessary credentials for these services. Note: these services may involve costs, always review their pricing details before use.

The `EmbeddingsSpaceComponent` interface defines the following methods:

## Constructor

To instantiate an `EmbeddingsSpaceComponent`, provide both an [`EmbeddingsGenerationModel`](01_core_abstractions.md#embeddingsgenerationmodel) and an [`EmbeddingsStore`](01_core_abstractions.md#embeddingsstore).

```java
EmbeddingsGenerationModel openAIModel = new OpenAIEmbeddingsGenerationModel("YOUR_OPENAI_API_KEY");
EmbeddingsStore pineconeStore = new PineconeEmbeddingsStore("YOUR_PINECONE_URL", "YOUR_PINECONE_API_KEY", "YOUR_PINECONE_SPACE");
EmbeddingsSpaceComponent embeddingsSpace = new EmbeddingsSpaceComponent(openAIModel, pineconeStore);
```

## `create`

Generates an embedding from a text without persisting it.

- **Parameters**:
    - `text`: The textual input for embedding.
    - `additionalMetadata`: Supplementary metadata associated with the text.

```java
String sampleText = "Hello, eLLMental!";
Map<String, String> additionalMetadata = new HashMap<>();
additionalMetadata.put("key", "value");
Embedding embedding = embeddingsSpace.create(sampleText, additionalMetadata);
```

## `save`

Generates and saves an embedding for a text.

- **Parameters**:
    - `text`: Text to be embedded.
    - `additionalMetadata`: (Optional) Additional metadata.

```java
String sampleText = "Hello, eLLMental!";
Embedding embedding = embeddingsSpace.save(sampleText, additionalMetadata);

// Or just
Embedding embedding = embeddingSpace.save(sampleText);
```

## `mostSimilarEmbeddings`

Fetches embeddings semantically closest to a reference text.

- **Parameters**:
    - `referenceText`: The text for comparison.
    - `limit`: Maximum number of similar embeddings to return.

```java
embeddingsSpace.save("Hello, eLLMental!");
List<Embedding> closestNeighbors = embeddingsSpace.mostSimilarEmbeddings("Greetings!", 3);
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

```java
String[][] textPairs = [["Man", "Woman"], ["Boy", "Girl"], ["King", "Queen"], ["Prince", "Princess"], ["Father", "Mother"]];
float[] relationshipVector = embeddingsSpace.calculateRelationshipVector(textPairs);
```

## `translateEmbedding`

Shifts an embedding in the embeddings space to find the location of the text that would meet the relationship represented by the vector.

- **Parameters**:
    - `embedding`: The primary embedding.
    - `vector`: The vector determining translation.

This is useful if you want to search for embeddings that are similar to a given one, but in a different context. For instance, let's say we have the following embedding:

```java
Embedding bullEmbedding = embeddingsSpace.create("Bull");
```

And a relationship vector calculated as follows:

```java
String[][] textPairs = [["Man", "Woman"], ["Boy", "Girl"], ["King", "Queen"], ["Prince", "Princess"], ["Father", "Mother"]];
float[] relationshipVector = embeddingsSpace.calculateRelationshipVector(textPairs);
```

We can use the relationship vector to find the location of the words that would be similar to "Cow" instead of "Bull". Notice that embeddings cannot be reversed, and we can't really know if this embedding represents a cow, but it will give us a good approximation that can be used to refine search results later.

```java
Embedding likelyACowEmbedding = embeddingsSpace.translateEmbedding(bullEmbedding, relationshipVector);

// This will return embeddings that are similar to "Cow"
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