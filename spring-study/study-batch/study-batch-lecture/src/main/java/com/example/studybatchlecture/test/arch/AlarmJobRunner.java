package com.example.studybatchlecture.test.arch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlarmJobRunner implements ApplicationRunner {

//    private final JobLauncher jobLauncher;
    private final BasicBatchConfigurer basicBatchConfigurer;
    private final AlarmSendJobConfig alarmSendJobConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SimpleJobLauncher simpleJobLauncher = (SimpleJobLauncher)basicBatchConfigurer.getJobLauncher();
        simpleJobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());

        List<JobParameters> jobParametersList = new ArrayList<>();
        jobParametersList.add(
                new JobParametersBuilder()
                        .addString("alarmSendTimeType", "live")
                        .addLong("broadcastNo", 1234L)
                        .addString("temp", LocalDateTime.now().toString())
                        .toJobParameters()
        );
        jobParametersList.add(
                new JobParametersBuilder()
                        .addString("alarmSendTimeType", "live")
                        .addLong("broadcastNo", 5678L)
                        .addString("temp", LocalDateTime.now().toString())
                        .toJobParameters()
        );

        jobParametersList.forEach(param -> {
            try {
                simpleJobLauncher.run(alarmSendJobConfig.alarmSendJob(), param);
            } catch (Exception e) {
                System.out.println("error~");
            }
        });
    }
}

// 비동기 job 실행 가능 여부 확인하기
// fallback job에서 job 정보 어떻게 조회할지...