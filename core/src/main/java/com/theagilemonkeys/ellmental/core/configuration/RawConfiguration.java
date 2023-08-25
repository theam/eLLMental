package com.theagilemonkeys.ellmental.core.configuration;

import java.util.List;

// This record allows to parse features as string or object
public record RawConfiguration(
        List<Object> features
) {
}
