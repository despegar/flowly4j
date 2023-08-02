package com.flowly4j.jpa;

import com.flowly4j.core.session.Execution;
import io.vavr.control.Option;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Embeddable
public class ExecutionWrapper {

    @Column(name = "EXECUTION_TASK_ID")
    private String taskId;

    @Column(name = "EXECUTION_TIME")
    private Instant at;

    @Column(name = "EXECUTION_MESSAGE")
    private String message;

    public ExecutionWrapper(Execution execution) {
        this.taskId = execution.getTaskId();
        this.at = execution.getAt();
        this.message = execution.getMessage().getOrNull();
    }

    public Execution toExecution() {
        return new Execution(taskId, at, Option.of(message));
    }

}