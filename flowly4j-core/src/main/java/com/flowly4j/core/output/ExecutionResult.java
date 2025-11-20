package com.flowly4j.core.output;

import com.flowly4j.core.context.ExecutionContext;
import com.flowly4j.core.context.ReadableExecutionContext;
import com.flowly4j.core.session.Session;
import com.flowly4j.core.session.Status;
import com.flowly4j.core.tasks.Task;

import java.util.Objects;

/**
 * Result of a Workflow execution
 */
public final class ExecutionResult {
    private final String sessionId;
    private final String taskId;
    private final Status status;
    private final ReadableExecutionContext executionContext;

    private ExecutionResult(String sessionId, String taskId, Status status, ReadableExecutionContext executionContext) {
        this.sessionId = sessionId;
        this.taskId = taskId;
        this.status = status;
        this.executionContext = executionContext;
    }

    public static ExecutionResult of(Session session, Task task, ExecutionContext executionContext) {
        return new ExecutionResult(session.getSessionId(), task.getId(), session.getStatus(), executionContext);
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getTaskId() {
        return taskId;
    }

    public Status getStatus() {
        return status;
    }

    public ReadableExecutionContext getExecutionContext() {
        return executionContext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExecutionResult that = (ExecutionResult) o;
        return Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(taskId, that.taskId) &&
                status == that.status &&
                Objects.equals(executionContext, that.executionContext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, taskId, status, executionContext);
    }

    @Override
    public String toString() {
        return "ExecutionResult{" +
                "sessionId='" + sessionId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", status=" + status +
                ", executionContext=" + executionContext +
                '}';
    }
}



