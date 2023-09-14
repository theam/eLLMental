# eLLMental

![eLLMental](docs-site/static/img/eLLMental-logo-black.svg#gh-light-mode-only)
![eLLMental](docs-site/static/img/eLLMental-logo-white.svg#gh-dark-mode-only)

eLLMental is the ultimate library of components for building LLM-driven projects in the JVM.

Wanna try? Go straight to the [Getting Started Guide](https://docs.ellmental.com/getting-started), or keep reading to know more about eLLMental.

## What can you do with eLLMental?

eLLMental is divided into components that can be installed and used independently. Here's a summary of the available functionality:

### Embeddings Space Component

Embedding models are a special kind of Large Language Models (LLMs) that allow, given a piece of text, to calculate a large vector that represents a point in what we call the embeddings space. This embeddings space has the property that two pieces of text that are semantically related will be placed close to each other, allowing us to calculate a semantic distance between any two given pieces of text. Embeddings can be used to implement powerful search features that go beyond keyword matching, find related documents in a large database, or detect redundant information even when it's written in different ways.

The Embeddings Space Component provides straightforward interfaces to create and operate with embeddings, find the semantically closest documents to a given piece of text and many other operations. See the Embeddings Semantic Search Component documentation page for more details.

## Getting started

### Installing eLLMental

eLLMental provides composable abstractions for common patterns used in AI applications. For instance, to build a semantic search service with a REST API, using the OpenAI embeddings model and Pinecone as the database, this is what you have to do:

You just need to add the eLLMental dependencies in your `build.gradle` file:

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.theagilemonkeys.ellmental:core:0.0.2"
    implementation "com.theagilemonkeys.ellmental:embeddingsgeneration:0.0.2"
    implementation "com.theagilemonkeys.ellmental:embeddingsstore:0.0.2"
    implementation "com.theagilemonkeys.ellmental:embeddingsspace:0.0.2"
    // Other dependencies...
}
```

## eLLMental Principles

These are the design principles behind eLLMental:

1. **Simplicity in Complexity:** We aim to make the AI development process as simple and intuitive as any other library, hiding  implementation details and glue code so the developer can focus on creating value.

2. **Readiness for Production:** From development to deployment, all features of eLLMental are crafted with a production-ready mindset.

3. **Continuous Improvement:** eLLMental continuously evolves for the better. With the support of our active community and dedicated team, we regularly add improvements and introduce new features.

## Join the movement!

We'll need your help to build something that becomes really useful for everyone. There are many things you can do to contribute:

1. Join the conversation in our [Discord server](https://discord.gg/ZajFQEjgFw).
2. Send us suggestions, questions, or feature requests by creating a [New Issue](https://github.com/theam/ellmental/issues/new).
3. Look at the [Open Issues](https://github.com/theam/ellmental/issues), choose one that interests you, and start contributing.
4. Spread the word about eLLMental!

## License

The eLLMental project operates under the terms of the Apache License, Version 2.0. You can view the full license in the [LICENSE](LICENSE) file.

eLLMental is a proud open-source initiative by [The Agile Monkeys](https://www.theagilemonkeys.com/).

## Contact

Your feedback and questions are important to us. For any inquiries or issues, feel free to reach out to us at [info@theagilemonkeys.com](mailto:info@theagilemonkeys.com).