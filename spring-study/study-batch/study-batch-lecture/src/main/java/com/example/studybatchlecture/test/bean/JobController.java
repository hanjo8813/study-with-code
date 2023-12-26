package com.example.studybatchlecture.test.bean;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    private final JobLauncher jobLauncher;
    private final JobConfig jobConfig;

    @GetMapping("/run")
    public void run() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters param = new JobParametersBuilder()
                .addString("temp", LocalDateTime.now().toString())
                .toJobParameters();
        jobLauncher.run(jobConfig.job(), param);
    }
}
