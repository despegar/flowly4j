package com.flowly4j.core.repository;

import com.flowly4j.core.repository.model.Session;

public interface Repository {

    Session create();

    Session get(String sessionId);

    Session save(Session session);

}