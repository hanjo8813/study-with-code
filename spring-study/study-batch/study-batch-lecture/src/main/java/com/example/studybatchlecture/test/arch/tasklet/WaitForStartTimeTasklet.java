package com.example.studybatchlecture.test.arch.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class WaitForStartTimeTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("----- WaitForStartTimeTasklet -----");

        try {
            Thread.sleep(1000L);
//            throw new RuntimeException();
            Thread.sleep(1000L);
        } catch (Exception e) {
            System.out.println("sleep ex");
            throw new RuntimeException();
        }

        System.out.println("wake up!");

        return RepeatStatus.FINISHED;
    }
}
