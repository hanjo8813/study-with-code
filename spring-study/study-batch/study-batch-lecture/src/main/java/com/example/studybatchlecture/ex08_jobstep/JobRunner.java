package com.example.studybatchlecture.ex08_jobstep;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JobRunner implements ApplicationRunner {

    private final JobLauncher jobLauncher;
    private final JobConfig jobConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jobLauncher.run(jobConfig.parentJob(), new JobParametersBuilder().addDate("dateKey", new Date()).toJobParameters());
    }
}
