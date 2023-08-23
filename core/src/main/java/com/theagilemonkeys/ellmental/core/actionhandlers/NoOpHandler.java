package com.theagilemonkeys.ellmental.core.actionhandlers;

import com.google.gson.JsonElement;

public class NoOpHandler implements ActionHandler {
    @Override
    public JsonElement handle(JsonElement actionJson) {
        return actionJson;
    }
}
