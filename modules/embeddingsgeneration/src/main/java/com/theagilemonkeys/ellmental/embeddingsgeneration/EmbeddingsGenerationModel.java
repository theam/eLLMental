package com.theagilemonkeys.ellmental.embeddingsgeneration;


import com.google.gson.Gson;
import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.actions.GenerateEmbedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.openai.worker.OpenAiWorker;

import static com.theagilemonkeys.ellmental.core.WorkerManager.*;

public class EmbeddingsGenerationModel {
    public static void init() {
        ensureWorkerManagerIsRunning();
        var worker = new OpenAiWorker();
        registerWorkerInstance(worker);
    }

    public static Embedding generateEmbedding(String text) {
        init();
        var result = runActionSync(GenerateEmbedding.action(text));
        return new Gson().fromJson(result, Embedding.class);
    }
}