package com.theagilemonkeys.ellmental.core;

public class TestHandler implements CommandHandler {
    @Override
    public String handle(String commandJson) {
        return "test";
    }
}
