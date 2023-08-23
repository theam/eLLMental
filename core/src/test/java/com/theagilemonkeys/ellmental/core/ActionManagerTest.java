package com.theagilemonkeys.ellmental.core;

import com.google.gson.JsonNull;
import com.theagilemonkeys.ellmental.core.actions.ActionResult;
import org.junit.jupiter.api.Test;

class ActionManagerTest {

    public static class TestWorker implements Worker<TestWorker.State, TestWorker.Msg> {

        record State(
                int counter
        ) {
        }

        sealed interface Msg {
        }

        record Increment() implements Msg {
        }

        record Decrement() implements Msg {
        }

        record NoOp() implements Msg {
        }


        public State initialState() {
            return new State(0);
        }

        public Msg parseMessage(ActionResult actionResult) {
            return switch (actionResult.messageName()) {
                case "Increment" -> new Increment();
                case "Decrement" -> new Decrement();
                default -> new NoOp();
            };
        }

        public UpdateResult<State> update(State current, Msg msg) {
            var counter = current.counter();
            if (msg instanceof Increment) {
                return UpdateResult.of(new State(counter + 1));
            } else {
                return UpdateResult.of(new State(counter - 1));
            }
        }
    }

    @Test
    void testExample() {
        var mod = new TestWorker();
        var am = new ActionManager<>(mod);
        am.run();
        am.sendMessage("Increment", JsonNull.INSTANCE);
    }
}