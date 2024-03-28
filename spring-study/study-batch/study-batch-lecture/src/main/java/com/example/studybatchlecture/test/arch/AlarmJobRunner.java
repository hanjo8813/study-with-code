package com.example.studybatchlecture.test.arch;

import com.example.studybatchlecture.test.arch.job.AlarmSendJobConfig;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
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

    private final JobRepository jobRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        runNewExecution();
    }


    private void runNewExecution() throws Exception {
        SimpleJobLauncher simpleJobLauncher = (SimpleJobLauncher)basicBatchConfigurer.getJobLauncher();
        simpleJobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());

        List<JobParameters> jobParametersList = new ArrayList<>();
        jobParametersList.add(
                new JobParametersBuilder()
                        .addString("alarmSendTimeType", "live")
                        .addLong("broadcastSeq", 1001L)
//                        .addString("temp", LocalDateTime.now().toString())
                        .toJobParameters()
        );
//        jobParametersList.add(
//                new JobParametersBuilder()
//                        .addString("alarmSendTimeType", "live")
//                        .addLong("broadcastNo", 5678L)
//                        .addString("temp", LocalDateTime.now().toString())
//                        .toJobParameters()
//        );

        for(var param : jobParametersList) {
            simpleJobLauncher.run(alarmSendJobConfig.alarmSendJob(), param);
        }
    }

}

// 비동기 job 실행 가능 여부 확인하기
// fallback job에서 job 정보 어떻게 조회할지...