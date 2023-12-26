package com.example.studybatchlecture.test.bean;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class Tasklet1 implements Tasklet {

    private boolean isFirst = true;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("------ tasklet 1 ------");
        System.out.println(isFirst);

        isFirst = false;

        return RepeatStatus.FINISHED;
    }
}
