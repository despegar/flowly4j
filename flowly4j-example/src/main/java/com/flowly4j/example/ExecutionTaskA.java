package com.flowly4j.example;

import com.flowly4j.core.context.WritableExecutionContext;
import com.flowly4j.core.input.Key;
import com.flowly4j.core.tasks.ExecutionTask;
import com.flowly4j.core.tasks.Task;
import com.flowly4j.core.tasks.compose.alternative.Alternative;
import com.flowly4j.core.tasks.compose.condition.Condition;
import com.flowly4j.core.tasks.compose.retry.Retry;
import io.vavr.collection.List;

import java.time.Instant;

import static com.flowly4j.example.CustomKeys.*;

public class ExecutionTaskA extends ExecutionTask {

    public ExecutionTaskA() {
        super(Condition.of(c -> c.contains(KEY6)), Alternative.of(new FinishB()), Retry.of(Strategies.NOW, Strategies.TEN_TIMES));
    }

    @Override
    public Task next() {
        return new ExecutionTaskB();
    }

    @Override
    protected void perform(WritableExecutionContext executionContext) {
        System.out.println("XXXXXXX");
        executionContext.set(KEY1, "esta es una prueba");
        executionContext.set(KEY2, 123);
        executionContext.set(KEY4, Person.of("juan", 123, Instant.now()));
        throw new RuntimeException("se rompe");
    }

    @Override
    protected List<Key> customAllowedKeys() {
        return List.of(KEY2, KEY1, KEY6);
    }

}