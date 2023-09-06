package com.theagilemonkeys.ellmental.textgeneration.openai;

import com.theagilemonkeys.ellmental.textgeneration.TextGenerationService;
import com.theagilemonkeys.ellmental.textgeneration.openai.errors.NoContentFoundException;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class OpenAiChatGenerationModel extends TextGenerationService<ChatMessage> {
    private static final Double DEFAULT_TEMPERATURE = 0.7;
    private static final int DEFAULT_MAX_TOKENS = 3000;
    private static final Logger log = LoggerFactory.getLogger(OpenAiChatGenerationModel.class);
    private final Double temperature;
    private final int maxTokens;
    private final OpenAiModels model;
    // The OpenAI client is package-private to allow injecting a mock in tests
    OpenAiService openAiService;

    /**
     * Constructor for OpenAiTextGenerationModel that uses default values for temperature (0.7),
     * maxTokens (3000) and model (GPT-3.5).
     * @param openAiKey OpenAI API key
     */
    public OpenAiChatGenerationModel(String openAiKey) {
        this(openAiKey, OpenAiModels.GPT_3_5, DEFAULT_TEMPERATURE, DEFAULT_MAX_TOKENS);
    }

    /**
     * Constructor for OpenAiTextGenerationModel that explicitly sets the model, but uses the default values for
     * temperature (0.7) and maxTokens (3000).
     * @param openAiKey OpenAI API key
     * @param model Model to use for the chat generation
     */
    public OpenAiChatGenerationModel(String openAiKey, OpenAiModels model) {
        this(openAiKey, model, DEFAULT_TEMPERATURE, DEFAULT_MAX_TOKENS);
    }

    /**
     * Constructor for OpenAiTextGenerationModel that explicitly sets the temperature, maxTokens and model.
     * @param openAiKey OpenAI API key
     * @param temperature Temperature to use for the chat generation
     * @param maxTokens Maximum number of tokens to use for the chat generation
     * @param model Model to use for the chat generation
     */
    public OpenAiChatGenerationModel(String openAiKey, OpenAiModels model, Double temperature, int maxTokens) {
        this.openAiService = new OpenAiService(openAiKey, Duration.ofSeconds(240));
        this.temperature = temperature;
        this.maxTokens = maxTokens;
        this.model = model;
    }

    /**
     * Generates a chat response using the OpenAI API.
     * @param chatMessages List of chat messages to use as context for the response
     * @return Generated chat response
     */
    @Override
    public String generate(List<ChatMessage> chatMessages) {
        log.debug("Generating chat response for chat messages {}", chatMessages);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .messages(chatMessages)
                .temperature(temperature)
                .maxTokens(maxTokens)
                .model(model.getCodename())
                .build();

        ChatCompletionChoice chatCompletionChoice = openAiService.createChatCompletion(chatCompletionRequest).getChoices().get(0);
        String chatCompletionContent = chatCompletionChoice.getMessage().getContent();

        log.debug("Chat completion response is {}", chatCompletionContent);

        if (chatCompletionContent.isEmpty()) {
            throw new NoContentFoundException(chatMessages);
        }

        return chatCompletionContent;
    }
}
