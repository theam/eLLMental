package com.theagilemonkeys.ellmental.embeddingsgeneration.openai.worker;

import com.google.gson.Gson;
import com.theagilemonkeys.ellmental.core.UpdateResult;
import com.theagilemonkeys.ellmental.core.Worker;
import com.theagilemonkeys.ellmental.core.actions.ActionResult;
import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.actions.GenerateEmbedding;

import java.util.Optional;

// This is an example class of a worker that manages some state
public class OpenAiWorker implements Worker<OpenAiWorker.State, OpenAiWorker.Message> {


    ////////////////////////////////////////////////////////
    //                                                    //
    //    STATE MANAGEMENT                                //
    //                                                    //
    ////////////////////////////////////////////////////////
    record State(
            Optional<Embedding> lastEmbedding
    ) {
    }

    public State initialState() {
        return new State(
                Optional.empty()
        );
    }

    public UpdateResult<State> update(State state, Message message) {
        if (message instanceof PerformEmbeddingGeneration generateEmbedding) {
            return new UpdateResult<>(
                    state,
                    GenerateEmbedding.action(
                            generateEmbedding.text()
                    ).withMessage("EmbeddingGenerated")
            );
        } else if (message instanceof EmbeddingGenerated embeddingGenerated) {
            return UpdateResult.of(
                    new State(
                            Optional.of(embeddingGenerated.embedding())
                    )
            );
        } else {
            return UpdateResult.of(state);
        }
    }

    ////////////////////////////////////////////////////////
    //                                                    //
    //    MESSAGES DEFINITION                             //
    //                                                    //
    ////////////////////////////////////////////////////////
    sealed interface Message {
    }


    record PerformEmbeddingGeneration(String text) implements Message {
    }

    record EmbeddingGenerated(Embedding embedding) implements Message {
    }

    public Optional<Message> parseMessage(ActionResult actionResult) {
        Gson gson = new Gson();
        var value = actionResult.value();
        return switch (actionResult.messageName()) {
            case "PerformEmbeddingGeneration" -> Optional.of(
                    new PerformEmbeddingGeneration(gson.fromJson(value, String.class))
            );
            case "EmbeddingGenerated" -> Optional.of(
                    new EmbeddingGenerated(gson.fromJson(value, Embedding.class))
            );
            default -> Optional.empty();
        };
    }


    ////////////////////////////////////////////////////////
    //                                                    //
    //    REQUIRED METADATA                               //
    //                                                    //
    ////////////////////////////////////////////////////////
    @Override
    public String getWorkerName() {
        return OpenAiWorker.class.getSimpleName();
    }

}
