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

## `save`

Generates and persists an embedding for a given text.

- **Parameters**:
    - `text`: Text to be embedded.
- **Returns**: The generated embedding.

```java
String sampleText = "Hello, eLLMental!";
Embedding embedding = embeddingsSpace.save(sampleText);
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