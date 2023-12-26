package com.example.studybatchlecture.test.arch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlarmSendJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("----- before job -----");

        try {
            Thread.sleep(2000L);
        } catch (Exception e) {
            System.out.println("sleep ex");
        }

        System.out.println("wake up!");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
