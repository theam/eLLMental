package com.theagilemonkeys.ellmental.embeddingsstore.actions;

import com.google.gson.Gson;
import com.theagilemonkeys.ellmental.core.actions.Action;
import com.theagilemonkeys.ellmental.core.schema.Embedding;

import java.util.Map;

public class StoreEmbedding {

    public record ActionInput(
            Embedding embedding,
            Map<String, String> metadata
    ) {
    }

    public static String name = StoreEmbedding.class.getSimpleName();

    public static Action action(Embedding embedding, Map<String, String> metadata) {
        var gson = new Gson();
        var input = new ActionInput(embedding, metadata);
        return Action
                .named(name)
                .withValue(gson.toJsonTree(new ActionInput(embedding, metadata)));
    }
}
