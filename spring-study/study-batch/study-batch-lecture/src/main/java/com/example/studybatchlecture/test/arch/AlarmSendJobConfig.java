package com.example.studybatchlecture.test.arch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AlarmSendJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final AlarmSendJobListener alarmSendJobListener;
    private final ExtractTargetMemberTasklet extractTargetMemberTasklet;
//    private final TargetMemberReader targetMemberReader;
//    private SendAlarmWriter sendAlarmWriter;

    @Bean
    public Job alarmSendJob() {
        return jobBuilderFactory.get("alarmSendJob")
                .start(extractTargetMemberStep())
//                .next()
                .listener(alarmSendJobListener)
                .build();
    }

    @Bean
    public Step extractTargetMemberStep() {
        return stepBuilderFactory.get("extractTargetMemberStep")
                .tasklet(extractTargetMemberTasklet)
                .build();
    }

//    @Bean
//    public Step sendAlarmStep() {
//        return stepBuilderFactory.get("sendAlarmStep")
//                .<Integer, Integer>chunk(1)
//                .reader()
//                .writer()
//    }

}
