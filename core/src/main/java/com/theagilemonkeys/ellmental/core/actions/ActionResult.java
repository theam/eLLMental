package com.theagilemonkeys.ellmental.core.actions;

import com.google.gson.JsonElement;

public record ActionResult(
        String messageName,
        JsonElement value
) {
}
