package com.theagilemonkeys.ellmental.core.actionhandlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HandlerManager {
    private HashMap<String, ActionHandler> handlers;

    private HandlerManager() {
        handlers = new HashMap<>();
    }

    public static HandlerManager load(Map<String, ActionHandler> handlers) {
        HandlerManager manager = new HandlerManager();
        manager.handlers.putAll(handlers);
        return manager;
    }

    public HandlerManager addHandler(String name, ActionHandler handler) {
        handlers.put(name, handler);
        return this;
    }

    public Optional<ActionHandler> getHandler(String name) {
        return Optional.ofNullable(handlers.get(name));
    }
}
