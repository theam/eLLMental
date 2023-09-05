package com.theagilemonkeys.ellmental.textgeneration.openai;

import com.theagilemonkeys.ellmental.textgeneration.TextGenerationService;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.List;

import static com.theagilemonkeys.ellmental.textgeneration.openai.errors.Constants.NO_CONTENT_FOUND_OPEN_AI;

public class OpenAiTextGenerationService extends TextGenerationService<List<ChatMessage>, OpenAiModels> {

    private final OpenAiService client;

    public OpenAiTextGenerationService(String openAiKey) {
        this.client = new OpenAiService(openAiKey, Duration.ofSeconds(240));
    }

    // Only visible for testing purposes
    OpenAiTextGenerationService(OpenAiService client) {
        this.client = client;
    }

    @Override
    public String generate(List<ChatMessage> chatMessages, Double temperature, int maxTokens, OpenAiModels model) {
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .messages(chatMessages)
                .temperature(temperature)
                .maxTokens(maxTokens)
                .model(model.getCodename())
                .build();

        ChatCompletionChoice chatCompletionChoice = client.createChatCompletion(chatCompletionRequest).getChoices().get(0);
        String chatCompletionContent = chatCompletionChoice.getMessage().getContent();

        return !chatCompletionContent.isEmpty() ?
                chatCompletionContent :
                String.format(NO_CONTENT_FOUND_OPEN_AI, chatMessages);
    }
}
