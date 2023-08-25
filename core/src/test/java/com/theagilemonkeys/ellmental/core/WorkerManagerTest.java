package com.theagilemonkeys.ellmental.core;

import com.google.gson.JsonNull;
import org.junit.jupiter.api.Test;

class WorkerManagerTest {
    @Test
    void testExample() {
        var workerManager = new WorkerManager();
        workerManager.run();
        workerManager.sendMessage("Increment", JsonNull.INSTANCE);
    }
}