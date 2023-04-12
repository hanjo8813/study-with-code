package com.example.oldbatch.basic1;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@RequiredArgsConstructor
//@Component
public class JobRunner implements ApplicationRunner {

    private final JobLauncher jobLauncher;
    private final BasicJobConfig basicJob;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /**
         * key or param 둘 중 하나만 바뀌어도 다른 job instance 라고 인식함
         */
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("stringKey", "string param1")
                .addLong("longKey", 2L)
                .addDate("dateKey", new Date())
                .addDouble("doubleKey", 16.5)
                .toJobParameters();

        jobLauncher.run(basicJob.job(), jobParameters);
    }
}
