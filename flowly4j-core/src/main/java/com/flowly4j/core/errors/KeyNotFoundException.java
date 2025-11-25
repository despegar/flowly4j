package com.flowly4j.core.errors;

public class KeyNotFoundException extends RuntimeException {

    private final String key;

    public KeyNotFoundException(String key) {
        super(String.format("Key %s not found in Execution Context", key));
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
