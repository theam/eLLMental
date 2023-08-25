package com.theagilemonkeys.ellmental.core.configuration;

import java.util.Map;

public record Feature(
        String className,
        Map<String, String> options
) {
    public String getClassName() {
        return className;
    }
}
