package com.theagilemonkeys.ellmental.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class StateManager {
    private static final StateManager instance = new StateManager();
    private final Map<String, AtomicReference<Object>> states;

    private StateManager() {
        this.states = new HashMap<>();
    }

    public static StateManager getInstance() {
        return instance;
    }

    public void put(String key, Object value) {
        if (!states.containsKey(key)) {
            states.put(key, new AtomicReference<>(value));
        } else {
            states.get(key).set(value);
        }
    }

    public Object get(String key) {
        if (!states.containsKey(key)) {
            states.put(key, new AtomicReference<>());
        }
        return states.get(key).get();
    }
}
