# Text generation module

The text generation module aims to give the user an abstraction to use any model to give final answers to user questions. 

Below, you can find the current implementations available.

## OpenAI implementation

You can use the `OpenAiTextGenerationService` to work with openAI chat messaging system:

```java
Double temperature = 0.1;
int maxTokens = 3000;
ChatMessage chatMessage = new ChatMessage("user", "what is an LLM?");

TextGenerationService<List<ChatMessage>, OpenAiModels> service = new OpenAiTextGenerationService("api key");
String result = service.generate(List.of(chatMessage), temperature, maxTokens, OpenAiModels.GPT_3_5);
```

The only requisite to have your openAI types ready to use is to add the openAI dependency to your `build.gradle` file:

```gradle
dependencies {
    implementation 'com.theokanning.openai-gpt3-java:service:0.16.0'
    ...
}
```
