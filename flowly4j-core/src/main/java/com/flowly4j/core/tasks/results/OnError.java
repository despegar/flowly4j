package com.flowly4j.core.tasks.results;

/**
 * There was an unexpected error during current workflow execution
 *
 */
public class OnError implements TaskResult {
    public final Throwable cause;
    public OnError(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "OnError{" +
                "cause=" + cause +
                '}';
    }
}
