package com.theagilemonkeys.ellmental.core;

import com.google.gson.JsonNull;
import com.theagilemonkeys.ellmental.core.actions.ActionResult;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class WorkerManagerTest {

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


        @Override
        public String getWorkerName() {
            return TestWorker.class.getSimpleName();
        }

        public State initialState() {
            return new State(0);
        }

        public Optional<Msg> parseMessage(ActionResult actionResult) {
            return switch (actionResult.messageName()) {
                case "Increment" -> Optional.of(new Increment());
                case "Decrement" -> Optional.of(new Decrement());
                default -> Optional.empty();
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
        var am = new WorkerManager();
        am.registerWorker(mod.getWorkerName(), mod);
        am.run();
        am.sendMessage("Increment", JsonNull.INSTANCE);
    }
}