package com.example.studybatchlecture.test.arch.job;

import com.example.studybatchlecture.test.arch.chunk.SendAlarmWriter;
import com.example.studybatchlecture.test.arch.chunk.TargetMemberReader;
import com.example.studybatchlecture.test.arch.tasklet.ExtractTargetMemberTasklet;
import com.example.studybatchlecture.test.arch.tasklet.FindBroadcastInfoTasklet;
import com.example.studybatchlecture.test.arch.tasklet.WaitForStartTimeTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AlarmSendJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final FindBroadcastInfoTasklet findBroadcastInfoTasklet;
    private final WaitForStartTimeTasklet waitForStartTimeTasklet;
    private final ExtractTargetMemberTasklet extractTargetMemberTasklet;

    private final TargetMemberReader targetMemberReader;
    private final SendAlarmWriter sendAlarmWriter;

    @Bean
    public Job alarmSendJob() {
        return jobBuilderFactory.get("alarmSendJob")
                .start(findBroadcastInfoStep())
                .next(waitForStartTimeStep())
                .next(extractTargetMemberStep())
                .next(sendAlarmStep())
                .build();
    }

    @Bean
    @JobScope
    public Step findBroadcastInfoStep() {
        return stepBuilderFactory.get("findBroadcastInfoStep")
                .tasklet(findBroadcastInfoTasklet)
                .build();
    }

    @Bean
    @JobScope
    public Step waitForStartTimeStep() {
        return stepBuilderFactory.get("waitForStartTimeStep")
                .tasklet(waitForStartTimeTasklet)
                .build();
    }

    @Bean
    @JobScope
    public Step extractTargetMemberStep() {
        return stepBuilderFactory.get("extractTargetMemberStep")
                .tasklet(extractTargetMemberTasklet)
                .build();
    }

    @Bean
    @JobScope
    public Step sendAlarmStep() {
        return stepBuilderFactory.get("sendAlarmStep")
                .<Long, Long>chunk(1)
                .reader(targetMemberReader)
                .writer(sendAlarmWriter)
                .build();
    }
}
