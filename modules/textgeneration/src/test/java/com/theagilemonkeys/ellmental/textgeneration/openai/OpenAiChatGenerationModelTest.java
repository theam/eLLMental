package com.theagilemonkeys.ellmental.textgeneration.openai;

import com.theagilemonkeys.ellmental.textgeneration.openai.errors.NoContentFoundException;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OpenAiChatGenerationModelTest {
    @Mock
    private OpenAiService openAiService;
    private OpenAiChatGenerationModel openAiChatGenerationModel;

    private final List<ChatMessage> chatMessages = List.of(new ChatMessage("user", "How can I rank up in Rocket League?"));
    private final Double temperature = 0.1;
    private final int maxTokens = 3000;
    private final OpenAiModels model = OpenAiModels.GPT_4;

    @BeforeEach
    public void setUp() {
        openAiChatGenerationModel = new OpenAiChatGenerationModel("openAiKey", model, temperature, maxTokens);
        openAiChatGenerationModel.openAiService = openAiService;
    }

    @Test
    public void testGenerate() {
        String chatResult = "test content";
        ChatCompletionChoice chatCompletionChoice = new ChatCompletionChoice();
        chatCompletionChoice.setMessage(new ChatMessage("user", chatResult));
        ChatCompletionResult result = new ChatCompletionResult();
        result.setChoices(List.of(chatCompletionChoice));
        when(openAiService.createChatCompletion(getChatCompletion())).thenReturn(result);

        String generatedText = openAiChatGenerationModel.generate(chatMessages);

        assertEquals(chatResult, generatedText);

        verifyNoMoreInteractions(openAiService);
    }

    @Test
    public void testGenerateWhenContentNotFound() {
        ChatCompletionChoice chatCompletionChoice = new ChatCompletionChoice();
        chatCompletionChoice.setMessage(new ChatMessage("user", ""));
        ChatCompletionResult emptyResult = new ChatCompletionResult();
        emptyResult.setChoices(List.of(chatCompletionChoice));
        when(openAiService.createChatCompletion(getChatCompletion())).thenReturn(emptyResult);

        assertThrows(NoContentFoundException.class, () -> openAiChatGenerationModel.generate(chatMessages));

        verifyNoMoreInteractions(openAiService);
    }

    private ChatCompletionRequest getChatCompletion() {
        return ChatCompletionRequest.builder()
                .messages(chatMessages)
                .temperature(temperature)
                .maxTokens(maxTokens)
                .model(model.getCodename())
                .build();
    }
}
