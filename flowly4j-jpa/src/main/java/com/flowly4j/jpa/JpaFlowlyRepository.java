package com.flowly4j.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowly4j.core.repository.Repository;
import com.flowly4j.core.session.Session;
import com.flowly4j.core.session.Status;
import io.vavr.collection.Iterator;
import io.vavr.control.Option;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.time.Instant;

public class JpaFlowlyRepository implements Repository {

    private final EntityManager entityManager;
    private final ObjectMapper objectMapper;

    public JpaFlowlyRepository(EntityManager entityManager, ObjectMapper objectMapper) {
        this.entityManager = entityManager;
        this.objectMapper = objectMapper;
    }

    @Override
    public Option<Session> get(String sessionId) {
        try {

            Option<SessionWrapper> session = Option.of(entityManager.find(SessionWrapper.class, sessionId));

            return session.map(s -> s.toSession(objectMapper));

        } catch (Throwable throwable) {
            throw new PersistenceException("Error getting session " + sessionId, throwable);
        }
    }

    @Override
    public Session insert(Session session) {
        try {

            SessionWrapper sessionWrapper = new SessionWrapper(session, objectMapper);
            entityManager.getTransaction().begin();
            entityManager.persist(sessionWrapper);
            entityManager.getTransaction().commit();

            return session;

        } catch (Throwable throwable) {
            throw new PersistenceException("Error inserting session " + session.getSessionId(), throwable);
        }
    }

    @Override
    public Session update(Session session) {
        try {

            SessionWrapper sessionWrapper = new SessionWrapper(session, objectMapper);
            entityManager.getTransaction().begin();
            SessionWrapper updatedSession = entityManager.merge(sessionWrapper);
            entityManager.getTransaction().commit();

            return updatedSession.toSession(objectMapper);

        } catch (OptimisticLockException ex) {
            throw new OptimisticLockException("Session " + session.getSessionId() + " was modified by another transaction");
        } catch (Throwable throwable) {
            throw new PersistenceException("Error saving session " + session.getSessionId(), throwable);
        }
    }

    @Override
    public Iterator<String> getToRetry() {
        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
            Root<SessionWrapper> root = criteriaQuery.from(SessionWrapper.class);
            criteriaQuery.select(root.get("sessionId"));
            criteriaQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("status"), Status.TO_RETRY),
                            criteriaBuilder.lessThanOrEqualTo(root.get("attempts").get("nextRetry"), Instant.now())
                    )
            ).orderBy(criteriaBuilder.asc(root.get("attempts").get("nextRetry")));

            return Iterator.ofAll(entityManager.createQuery(criteriaQuery).getResultList());

        } catch (Throwable throwable) {
            throw new PersistenceException("Error getting sessions to retry", throwable);
        }
    }
}
