package com.example.oldbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BasicJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job basicJob() {
        return jobBuilderFactory.get("basicJob")
                .start(basicStep1())
                .next(basicStep2())
                .build();
    }

    @Bean
    public Step basicStep1() {
        return stepBuilderFactory.get("basicStep1")
                .tasklet(
                        new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                                /**
                                 * 두가지 방식으로 파라미터를 뽑아낼 수 있음
                                 */
//                                JobParameters jobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
//                                System.out.println(jobParameters.getString("stringKey"));
//                                System.out.println(jobParameters.getLong("longKey"));
//                                System.out.println( jobParameters.getDate("dateKey"));
//                                System.out.println( jobParameters.getDouble("doubleKey"));

//                                Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();

                                System.out.println("basicStep1");
                                return RepeatStatus.FINISHED;
                            }
                        }
                )
                .build();
    }

    @Bean
    public Step basicStep2() {
        return stepBuilderFactory.get("basicStep2")
                .tasklet(
                        (contribution, chunkContext) -> {
                            System.out.println("basicStep2");
//                            throw new RuntimeException();
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }
}
