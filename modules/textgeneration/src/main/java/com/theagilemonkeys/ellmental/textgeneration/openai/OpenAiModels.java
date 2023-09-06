package com.theagilemonkeys.ellmental.textgeneration.openai;

public enum OpenAiModels {
    GPT_3_5_CONTEXT_16K("gpt-3.5-turbo-16k"),
    GPT_3_5("gpt-3.5-turbo"),
    GPT_4("gpt-4");

    private final String codename;

    OpenAiModels(String codename) {
        this.codename = codename;
    }

    public String getCodename() {
        return this.codename;
    }
}