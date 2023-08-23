package com.theagilemonkeys.ellmental.core.actions;

public class NoOp {
    public static String name = NoOp.class.getSimpleName();

    public static Action action() {
        return Action.named(name);
    }
}
