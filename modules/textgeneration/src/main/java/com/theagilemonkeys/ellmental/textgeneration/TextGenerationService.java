package com.theagilemonkeys.ellmental.textgeneration;

public abstract class TextGenerationService<Input, ProviderParameters> {
    public abstract String generate(Input input, Double temperature, int maxTokens, ProviderParameters parameters);
}
