package com.theagilemonkeys.ellmental.core;

import org.junit.jupiter.api.Test;

class ActionManagerTest {

    public static class TestModule {

        record State(
                int counter
        ) {
        }

        sealed interface Msg permits Increment, Decrement {
        }

        record Increment() implements Msg {
        }

        record Decrement() implements Msg {
        }


        public static UpdateResult<State> init() {
            return new UpdateResult<>(new State(0), new Command(new CommandInfo("init"), "{}"));
        }

        public static Msg sub(String jsonString) {
            if (jsonString.contains("increment")) {
                return new Increment();
            } else {
                return new Decrement();
            }
        }

        public static UpdateResult<State> update(State current, Msg msg) {
            var counter = current.counter();
            if (msg instanceof Increment) {
                if (counter == 10) {
                    return UpdateResult.noCommand(new State(0));
                }
                return new UpdateResult<>(
                        new State(counter + 1),
                        new Command(new CommandInfo("whatever"), "{}"
                        ));
            } else {
                return UpdateResult.noCommand(new State(counter - 1));
            }
        }
    }

    @Test
    void testExample() {
        var am = ActionManager
                .withUpdate(TestModule::update)
                .withInit(TestModule::init)
                .withSub(TestModule::sub)
                .build();
        am.run();
    }
}