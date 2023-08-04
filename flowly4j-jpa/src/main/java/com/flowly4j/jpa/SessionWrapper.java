package com.flowly4j.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowly4j.core.errors.SerializationException;
import com.flowly4j.core.session.Session;
import com.flowly4j.core.session.Status;
import io.vavr.collection.HashMap;
import io.vavr.control.Option;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@Entity
@Table(name = "WF_SESSION")
@NoArgsConstructor
@Getter
public class SessionWrapper {
    @Id
    @Column(name = "SESSION_ID")
    private String sessionId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "WF_SESSION_VARIABLES", joinColumns = @JoinColumn(name = "SESSION_ID"))
    @MapKeyColumn(name = "KEY")
    @Column(name = "VALUE")
    private Map<String, String> variables;

    @Column(name = "CREATE_AT")
    private Instant createAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @Version
    @Column(name = "VERSION")
    private Long version;

    private ExecutionWrapper lastExecution;

    private AttemptsWrapper attempts;

    public SessionWrapper(Session session, ObjectMapper objectMapper) {
        this.sessionId = session.getSessionId();
        this.variables = session.getVariables().mapValues(value -> {
            try {
                return objectMapper.writeValueAsString(value);
            } catch (Throwable cause) {
                throw new SerializationException("Error trying to serialize " + value, cause);
            }
        }).toJavaMap();
        this.lastExecution = session.getLastExecution().map(ExecutionWrapper::new).getOrNull();
        this.attempts = session.getAttempts().map(AttemptsWrapper::new).getOrNull();
        this.createAt = session.getCreateAt();
        this.status = session.getStatus();
        this.version = session.getVersion();
    }

    public Session toSession(ObjectMapper objectMapper) {
        return new Session(
                sessionId,
                HashMap.ofAll(variables).mapValues(value -> {
                    try {
                        return objectMapper.readValue(value, Object.class);
                    } catch (IOException cause) {
                        throw new SerializationException("Error trying to deserialize " + value, cause);
                    }
                }),
                Option.of(lastExecution).map(ExecutionWrapper::toExecution),
                Option.of(attempts).map(AttemptsWrapper::toAttempts),
                createAt,
                status,
                version
        );
    }
}
