package com.flowly4j.core.session;

import com.flowly4j.core.tasks.Task;
import io.vavr.control.Option;

import java.time.Instant;
import java.util.Objects;

/**
 * Execution Information
 */
public class Execution {

    /**
     * Last Task that was executed
     */
    private final String taskId;

    /**
     * When it was executed
     */
    private final Instant at;

    /**
     * Optional message about last execution
     */
    private final Option<String> message;

    public Execution(String taskId, Instant at, Option<String> message) {
        this.taskId = taskId;
        this.at = at;
        this.message = message;
    }

    public static Execution of(Task task) {
        return new Execution(task.getId(), Instant.now(), Option.none());
    }

    public static Execution of(Task task, String message) {
        return new Execution(task.getId(), Instant.now(), Option.of(message));
    }

    public String getTaskId() {
        return taskId;
    }

    public Instant getAt() {
        return at;
    }

    public Option<String> getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Execution execution = (Execution) o;
        return Objects.equals(taskId, execution.taskId) &&
                Objects.equals(at, execution.at) &&
                Objects.equals(message, execution.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, at, message);
    }

    @Override
    public String toString() {
        return "Execution{" +
                "taskId='" + taskId + '\'' +
                ", at=" + at +
                ", message=" + message +
                '}';
    }

}
