package com.example.studybatchlecture.ex03;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobRepositoryListener implements JobExecutionListener {

    private final JobRepository jobRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        JobParameters param = new JobParametersBuilder().addString("param", "6").toJobParameters();

        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, param);

        if (lastJobExecution != null) {
            for (StepExecution execution : lastJobExecution.getStepExecutions()) {
                System.out.println("status = " + execution.getStatus());
                System.out.println("exitStatus = " + execution.getExitStatus());
                System.out.println("stepName = " + execution.getStepName());
            }
        }
    }
}
