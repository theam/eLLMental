package com.theagilemonkeys.ellmental.core.errors;


public class MissingRequiredCredentialException extends RuntimeException {
    public MissingRequiredCredentialException(String message) {
        super(message);
    }
}