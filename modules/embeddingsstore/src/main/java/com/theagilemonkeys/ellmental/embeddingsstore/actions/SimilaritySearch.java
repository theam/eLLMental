package com.theagilemonkeys.ellmental.embeddingsstore.actions;

import com.google.gson.Gson;
import com.theagilemonkeys.ellmental.core.actions.Action;
import com.theagilemonkeys.ellmental.core.schema.Embedding;

public class SimilaritySearch {
    public static String name = SimilaritySearch.class.getSimpleName();

    public record ActionInput(
            Embedding reference,
            int limit
    ) {
    }

    public static Action action(Embedding reference, int limit) {
        var gson = new Gson();
        var input = new ActionInput(reference, limit);
        return Action
                .named(name)
                .withValue(gson.toJsonTree(new ActionInput(reference, limit)));
    }

}
