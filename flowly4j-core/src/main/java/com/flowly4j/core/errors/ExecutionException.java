package com.flowly4j.core.errors;

import com.flowly4j.core.session.Session;
import com.flowly4j.core.tasks.Task;

public class ExecutionException extends RuntimeException {

    private final Session session;
    private final Task task;

    public ExecutionException(Session session, Task task, Throwable cause) {
        super(cause);
        this.session = session;
        this.task = task;
    }

    public Session getSession() {
        return session;
    }

    public Task getTask() {
        return task;
    }

}
