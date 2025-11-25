package com.flowly4j.core.errors;

public class SessionNotFoundException extends RuntimeException {

    private final String sessionId;

    public SessionNotFoundException(String sessionId) {
        this.sessionId = sessionId;
    }

    public SessionNotFoundException(String sessionId, String message) {
        super(message);
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

}
