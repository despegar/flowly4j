package com.flowly4j.core.errors;

import com.flowly4j.core.input.Key;
import io.vavr.collection.List;

public class ParamsNotAllowedException extends RuntimeException {

    private final String taskId;
    private final List<Key> keys;

    public ParamsNotAllowedException(String taskId, List<Key> keys, String message) {
        super(message);
        this.taskId = taskId;
        this.keys = keys;
    }

    public String getTaskId() {
        return taskId;
    }

    public List<Key> getKeys() {
        return keys;
    }

}
