package com.example.oldbatch.basic2;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Tasklet2 implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("------------ tasklet2 ------------");

        ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
        ExecutionContext stepExecutionContext = contribution.getStepExecution().getExecutionContext();

        System.out.println("기존 ex 확인");
        System.out.println("job_ex_context : " + jobExecutionContext.get("job_ex_context"));
        System.out.println("step_ex_context : " + stepExecutionContext.get("step_ex_context"));

        if(stepExecutionContext.get("step_ex_context") == null){
            stepExecutionContext.put("step_ex_context", "step_2");
        }
        jobExecutionContext.put("job_ex_context_2", "job_temp_2");
        System.out.println("등록 완료");

        System.out.println("등록 확인");
        System.out.println("job_ex_context_2 : " + contribution.getStepExecution().getJobExecution().getExecutionContext().get("job_ex_context_2"));
        System.out.println("step_ex_context : " + contribution.getStepExecution().getExecutionContext().get("step_ex_context"));

        System.out.println("----------------------------------");
        return RepeatStatus.FINISHED;
    }
}
