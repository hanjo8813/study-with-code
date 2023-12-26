package com.example.studybatchlecture.test.arch;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobRunner implements ApplicationRunner {

    private final JobLauncher jobLauncher;
    private final AlarmSendJobConfig alarmSendJobConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("alarmSendTimeType", "live")
                .addLong("broadcastNo", 1234L)
                .addString("temp", LocalDateTime.now().toString())
                .toJobParameters();
        jobLauncher.run(alarmSendJobConfig.alarmSendJob(), jobParameters);
    }
}
