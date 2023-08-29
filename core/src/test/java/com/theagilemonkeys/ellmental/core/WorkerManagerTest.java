package com.theagilemonkeys.ellmental.core;

import com.google.gson.JsonNull;
import org.junit.jupiter.api.Test;

import static com.theagilemonkeys.ellmental.core.WorkerManager.sendMessage;

class WorkerManagerTest {
    @Test
    void testExample() {
        sendMessage("Increment", JsonNull.INSTANCE);
    }
}