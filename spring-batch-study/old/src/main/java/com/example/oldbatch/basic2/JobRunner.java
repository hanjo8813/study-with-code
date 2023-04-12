package com.example.oldbatch.basic2;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobRunner implements ApplicationRunner {

    private final JobLauncher jobLauncher;
    private final JobConfig jobConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jobLauncher.run(jobConfig.job(), new JobParameters());
    }
}
