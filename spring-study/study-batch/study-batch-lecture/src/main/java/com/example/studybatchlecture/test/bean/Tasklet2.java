package com.example.studybatchlecture.test.bean;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class Tasklet2 implements Tasklet {

    private boolean isFirst = true;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("------ tasklet 2 ------");
        System.out.println(isFirst);

        isFirst = false;

        return RepeatStatus.FINISHED;
    }
}
