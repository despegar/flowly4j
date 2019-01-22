package com.flowly4j.repository;

import com.flowly4j.errors.SessionNotFound;
import com.flowly4j.repository.model.Session;
import com.flowly4j.variables.Variables;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryRepository implements Repository {

    private Map<String, Session> storage = new HashMap<>();

    @Override
    public Session create(Variables initialVariables) {
        String id = UUID.randomUUID().toString();
        return save(new Session(id, initialVariables));
    }

    @Override
    public Session get(String sessionId) {
        if(!storage.containsKey(sessionId)) {
            throw new SessionNotFound(sessionId);
        }
        return storage.get(sessionId);
    }

    @Override
    public Session save(Session session) {
        storage.put(session.id, session);
        return session;
    }

}
