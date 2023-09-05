package com.theagilemonkeys.ellmental.textgeneration.openai;

import com.theagilemonkeys.ellmental.textgeneration.TextGenerationService;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

import static com.theagilemonkeys.ellmental.textgeneration.openai.errors.Constants.NO_CONTENT_FOUND_OPEN_AI;

public class OpenAiTextGenerationService extends TextGenerationService<List<ChatMessage>, OpenAiModels> {
    private static final Logger log = LoggerFactory.getLogger(OpenAiTextGenerationService.class);
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
        log.debug("Generating chat response for chat messages {}", chatMessages);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .messages(chatMessages)
                .temperature(temperature)
                .maxTokens(maxTokens)
                .model(model.getCodename())
                .build();

        ChatCompletionChoice chatCompletionChoice = client.createChatCompletion(chatCompletionRequest).getChoices().get(0);
        String chatCompletionContent = chatCompletionChoice.getMessage().getContent();

        log.debug("Chat completion response is {}", chatCompletionContent);

        return !chatCompletionContent.isEmpty() ?
                chatCompletionContent :
                String.format(NO_CONTENT_FOUND_OPEN_AI, chatMessages);
    }
}
