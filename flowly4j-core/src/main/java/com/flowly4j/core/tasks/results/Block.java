package com.flowly4j.core.tasks.results;

/**
 * Current workflow execution cannot continue because a condition is not met
 *
 */
public class Block implements TaskResult {
    @Override
    public String toString() {
        return "Block{}";
    }
}
