package com.example.oldbatch.ex12_flow;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomJobExecutionDecider implements JobExecutionDecider {

    private final boolean flag = false;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if (flag) {
            return new FlowExecutionStatus("O");
        }
        return new FlowExecutionStatus("X");
    }

}
