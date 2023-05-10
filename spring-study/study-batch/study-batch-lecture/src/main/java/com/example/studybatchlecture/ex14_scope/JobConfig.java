package com.example.studybatchlecture.ex14_scope;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JobScope / StepScope 기본 예제
 *
 * 1. job 실행 - job 리스너에서 jobContext 생성
 * 2. step1 실행 - step 리스너에서 stepContext 생성
 * 3. jobParam 주입받고 출력
 * 4. tasklet1 실행
 * 5. jobContext + StepContext 주입받고 출력
 */
@RequiredArgsConstructor
@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1(null))
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        jobExecution.getExecutionContext().putString("jobContext", "jobContext");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {

                    }
                })
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @JobScope
    @Bean
    public Step step1(@Value("#{jobParameters['jobParam']}") String jobParam) {
        System.out.println(jobParam);

        return stepBuilderFactory.get("step1")
                .tasklet(tasklet1(null, null))
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        stepExecution.getExecutionContext().putString("stepContext", "stepContext");
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return null;
                    }
                })
                .build();
    }

    @StepScope
    @Bean
    public Tasklet tasklet1(
            @Value("#{jobExecutionContext['jobContext']}") String jobContext,
            @Value("#{stepExecutionContext['stepContext']}") String stepContext
    ) {
        System.out.println(jobContext);
        System.out.println(stepContext);

        return (contribution, chunkContext) -> {
            System.out.println("tasklet 1");
            return RepeatStatus.FINISHED;
        };
    }

}