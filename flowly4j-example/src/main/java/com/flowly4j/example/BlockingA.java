package com.flowly4j.example;

import com.flowly4j.core.context.ExecutionContext;
import com.flowly4j.core.tasks.BlockingTask;
import com.flowly4j.core.tasks.Task;

import static com.flowly4j.example.CustomKeys.KEY2;

public class BlockingA extends BlockingTask {

    @Override
    public Boolean condition(ExecutionContext executionContext) {
        return true;
    }

    @Override
    public Task next() {
        return new FinishA();
    }

}