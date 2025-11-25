package com.flowly4j.core.errors;

public class DisjunctionTaskError extends RuntimeException {

    private final String taskId;

    public DisjunctionTaskError(String taskId, String message) {
        super(message);
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

}
