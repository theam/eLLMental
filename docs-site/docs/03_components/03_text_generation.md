# Text generation module

The text generation module provides an abstraction to use Large Language Models (LLMs) that, given a prompt, complete it with the most useful text possible. LLMs can be used for a very wide range of use cases, including summarizing text, drafting an essay, or getting answers for general knowledge questions. 

eLLMental provides the following implementations:

## `OpenAiChatGenerationModel`

The `OpenAiChatGenerationModel` is pre-configured to simulate a chat conversation. In this case, the model accepts a list of `ChatMessage` objects, where each message has a `user` and a `text` field. Then, the model will generate a response to the last message in the list. If you have used [ChatGPT](https://openai.com/chatgpt) before, the behavior will be very similar.

```java
ChatMessage chatMessage = new ChatMessage("user", "what is an LLM?");

// Using the simplified constructor, the library will set sensible defaults for the temperature, maxTokens and model.
OpenAiChatGenerationModel model = new OpenAiChatGenerationModel("<api key>");
String response = service.generate(List.of(chatMessage));
```

`OpenAiChatGenerationModel` is pre-configured to use the GPT-3.5 model by default, but if you have API access to more advanced models, you can specify them, as well as the temperature, maxTokens using the alternate constructors:

```java
// There's a convenience constructor that allows setting the model exclusively
OpenAiChatGenerationModel modelWithGPT4 = new OpenAiChatGenerationModel("<api key>", OpenAiModels.GPT_4);
OpenAiChatGenerationModel modelWithGPT316K = new OpenAiChatGenerationModel("<api key>", OpenAiModels.GPT_3_5_CONTEXT_16K);

// Or you can use the full constructor to set the model, temperature and maxTokens
OpenAiChatGenerationModel customModel = new OpenAiChatGenerationModel("<api key>", OpenAiModels.GPT_3_5_CONTEXT_16K, 0.5, 100);
```
