package com.theagilemonkeys.ellmental.textgeneration.openai.errors;

import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.List;

public class NoContentFoundException extends RuntimeException {
    private static final String NO_CONTENT_FOUND_MESSAGE = "No content found in response for messages %s";

    public NoContentFoundException(List<ChatMessage> messages) {
        super(String.format(NO_CONTENT_FOUND_MESSAGE, messages));
    }
}