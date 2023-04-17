package com.example.oldbatch.ex02;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Tasklet1 implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("------------ tasklet1 ------------");

        ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
        ExecutionContext stepExecutionContext = contribution.getStepExecution().getExecutionContext();

//        String jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
//        String stepName = chunkContext.getStepContext().getStepExecution().getStepName();

        jobExecutionContext.put("job_ex_context", "job_temp_1");
        stepExecutionContext.put("step_ex_context", "step_1");
        System.out.println("최초 등록 완료");

        System.out.println("등록 확인");
        System.out.println("job_ex_context : " + contribution.getStepExecution().getJobExecution().getExecutionContext().get("job_ex_context"));
        System.out.println("step_ex_context : " + contribution.getStepExecution().getExecutionContext().get("step_ex_context"));

        System.out.println("----------------------------------");

        return RepeatStatus.FINISHED;
    }
}
