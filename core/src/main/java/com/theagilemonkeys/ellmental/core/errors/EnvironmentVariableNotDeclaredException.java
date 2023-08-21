package com.theagilemonkeys.ellmental.core.errors;


public class EnvironmentVariableNotDeclaredException extends RuntimeException {
    public EnvironmentVariableNotDeclaredException(String message) {
        super(message);
    }
}