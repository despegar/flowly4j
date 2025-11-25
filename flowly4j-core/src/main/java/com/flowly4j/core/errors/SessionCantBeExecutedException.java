package com.flowly4j.core.errors;

public class SessionCantBeExecutedException extends RuntimeException {

    private final String sessionId;

    public SessionCantBeExecutedException(String sessionId, String message) {
        super(message);
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

}
