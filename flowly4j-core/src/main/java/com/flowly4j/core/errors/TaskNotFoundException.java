package com.flowly4j.core.errors;

public class TaskNotFoundException extends RuntimeException {

    private final String taskId;

    public TaskNotFoundException(String taskId, String message) {
        super(message);
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

}
