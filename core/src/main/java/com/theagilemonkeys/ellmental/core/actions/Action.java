package com.theagilemonkeys.ellmental.core.actions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Optional;

public record Action(ActionInfo info, JsonElement value, Optional<String> nextMessageName) {

    public static Action named(String name) {
        return new Action(new ActionInfo(name), new JsonObject(), Optional.empty());
    }

    public Action withValue(JsonElement newValue) {
        return new Action(info, newValue, nextMessageName);
    }

    public Action withMessage(String messageName) {
        return new Action(info, value, Optional.of(messageName));
    }
}
