package com.example.oldbatch.ex11_flow;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class CustomStepExecutionListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        String exitCode = stepExecution.getExitStatus().getExitCode();

        if(!exitCode.equals(ExitStatus.FAILED.getExitCode())){
            return new ExitStatus("CUSTOM");
        }

        return null;
    }
}
