package com.example.studybatchlecture.test.arch.job;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class TempJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("----- before job -----");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
