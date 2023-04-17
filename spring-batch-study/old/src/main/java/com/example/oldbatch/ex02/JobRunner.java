package com.example.oldbatch.ex02;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Job - Step Execution Context 간 공유 가능여부 확인하는 예제
 */
@RequiredArgsConstructor
@Component
public class JobRunner implements ApplicationRunner {

    private final JobLauncher jobLauncher;
    private final JobConfig jobConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jobLauncher.run(jobConfig.job(), new JobParametersBuilder().addDate("dateKey", new Date()).toJobParameters());
    }
}
