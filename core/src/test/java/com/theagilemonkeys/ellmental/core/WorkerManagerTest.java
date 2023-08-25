package com.theagilemonkeys.ellmental.core;

import com.google.gson.JsonNull;
import org.junit.jupiter.api.Test;

class WorkerManagerTest {

    @Test
    void testExample() {
        var am = new WorkerManager();
        am.run();
        am.sendMessage("Increment", JsonNull.INSTANCE);
    }
}