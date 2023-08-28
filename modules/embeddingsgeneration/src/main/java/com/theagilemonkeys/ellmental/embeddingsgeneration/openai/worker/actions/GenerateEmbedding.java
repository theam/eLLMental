package com.theagilemonkeys.ellmental.embeddingsgeneration.openai.worker.actions;

import com.google.gson.Gson;
import com.theagilemonkeys.ellmental.core.actions.Action;

public class GenerateEmbedding {
    public static String name = GenerateEmbedding.class.getSimpleName();

    public record ActionInput(
            String text
    ) {
    }

    public static Action action(String inputString) {
        var gson = new Gson();
        var input = new ActionInput(inputString);
        return Action
                .named(name)
                .withValue(gson.toJsonTree(input));
    }
}
