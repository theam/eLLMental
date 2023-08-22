package com.theagilemonkeys.ellmental.core;

// Helper class to use TestModule synchronously
public class EmbeddingGenerator {
    private final ActionManager<?, ?> actionManager;

    public EmbeddingGenerator(ActionManager<?, ?> actionManager) {
        this.actionManager = actionManager;
    }

    private static Command genEmbeddingCommand(String text) {
        return new Command(new CommandInfo("genEmbedding"), text);
    }

    public String genEmbedding(String text) {
        this.actionManager.executeCommand(genEmbeddingCommand(text));
        return this.actionManager.waitResult();
    }
}
