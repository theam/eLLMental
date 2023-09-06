package com.theagilemonkeys.ellmental.textgeneration;

import java.util.List;

public abstract class TextGenerationService<Input> {
    public abstract String generate(List<Input> input);
}
