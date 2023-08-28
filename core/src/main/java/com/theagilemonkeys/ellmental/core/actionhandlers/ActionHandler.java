package com.theagilemonkeys.ellmental.core.actionhandlers;

import com.google.gson.JsonElement;

public interface ActionHandler {
    JsonElement handle(JsonElement actionJson);

}
