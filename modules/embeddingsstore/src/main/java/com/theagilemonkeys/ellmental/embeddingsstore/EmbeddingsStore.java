package com.theagilemonkeys.ellmental.embeddingsstore;

import com.google.gson.Gson;
import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsstore.actions.SimilaritySearch;
import com.theagilemonkeys.ellmental.embeddingsstore.actions.StoreEmbedding;

import java.util.List;
import java.util.Map;

import static com.theagilemonkeys.ellmental.core.WorkerManager.ensureWorkerManagerIsRunning;
import static com.theagilemonkeys.ellmental.core.WorkerManager.runActionSync;

public class EmbeddingsStore {
    public static void init() {
        ensureWorkerManagerIsRunning();
    }

    public static void storeEmbedding(Embedding embedding, Map<String, String> metadata) {
        init();
        var result = runActionSync(StoreEmbedding.action(embedding, metadata));
    }

    public static List<Embedding> similaritySearch(Embedding reference, int limit) {
        init();
        var result = runActionSync(SimilaritySearch.action(reference, limit));
        return new Gson().fromJson(result, List.class);
    }
}