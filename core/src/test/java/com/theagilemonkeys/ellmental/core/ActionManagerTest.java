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
                return UpdateResult.noCommand(new State(counter + 1));
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

        am.executeCommand(new Command(new CommandInfo("whatever"), "{}"));
        am.executeCommand(new Command(new CommandInfo("whatever"), "{}"));
        am.executeCommand(new Command(new CommandInfo("whatever"), "{}"));
        am.executeCommand(new Command(new CommandInfo("whatever"), "{}"));
        am.executeCommand(new Command(new CommandInfo("test"), "{}"));
        var result = am.waitResult();
        System.out.println("GOT RESULT" + result);
    }
}