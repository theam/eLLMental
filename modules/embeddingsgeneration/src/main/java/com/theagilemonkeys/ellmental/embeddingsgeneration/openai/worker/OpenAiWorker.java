package com.theagilemonkeys.ellmental.embeddingsgeneration.openai.worker;

import com.google.gson.Gson;
import com.theagilemonkeys.ellmental.core.UpdateResult;
import com.theagilemonkeys.ellmental.core.Worker;
import com.theagilemonkeys.ellmental.core.actions.ActionResult;

public class OpenAiWorker implements Worker<OpenAiWorker.State, OpenAiWorker.Message> {

    private static final String DEFAULT_EMBEDDING_MODEL = "text-embedding-ada-002";

    record State(
            String embeddingOpenAiModel
    ) {
    }

    sealed interface Message {
    }


    record SetModel(String newModel) implements Message {
    }

    record Ignore() implements Message {
    }

    @Override
    public State initialState() {
        return new State(
                DEFAULT_EMBEDDING_MODEL
        );
    }

    @Override
    public Message parseMessage(ActionResult actionResult) {
        Gson gson = new Gson();
        var value = actionResult.value();
        return switch (actionResult.messageName()) {
            case "SetModel" -> new SetModel(gson.fromJson(value, String.class));
            default -> new Ignore();
        };
    }

    @Override
    public UpdateResult<State> update(State state, Message message) {
        if (message instanceof SetModel setModel) {
            return UpdateResult.of(
                    new State(
                            setModel.newModel
                    )
            );
        } else {
            return UpdateResult.of(state);
        }
    }

}
