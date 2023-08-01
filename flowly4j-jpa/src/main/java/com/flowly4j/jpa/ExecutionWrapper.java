package com.flowly4j.jpa;

import com.flowly4j.core.session.Execution;
import io.vavr.control.Option;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@Embeddable
public class ExecutionWrapper {

    @Column(name = "execution_task_id")
    private String taskId;

    @Column(name = "execution_time")
    private Instant at;

    @Column(name = "execution_message")
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