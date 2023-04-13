package com.example.oldbatch.basic2;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Tasklet3 implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("------------ tasklet2 ------------");

        ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
        ExecutionContext stepExecutionContext = contribution.getStepExecution().getExecutionContext();

        System.out.println("기존 ex 확인");
        System.out.println("job_ex_context : " + jobExecutionContext.get("job_ex_context"));
        System.out.println("job_ex_context_2 : " + jobExecutionContext.get("job_ex_context_2"));
        System.out.println("step_ex_context : " + stepExecutionContext.get("step_ex_context"));

        System.out.println("----------------------------------");
        return RepeatStatus.FINISHED;
    }
}
