package com.example.studybatchlecture.test.arch.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@JobScope
@Component
public class PrepareAlarmTasklet implements Tasklet, StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // 방송 정보 조회 -> context 저장
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("----- after step -----");

        try {
            Thread.sleep(2000L);
        } catch (Exception e) {
            System.out.println("sleep ex");
        }

        System.out.println("wake up!");

        return ExitStatus.COMPLETED;
    }
}
