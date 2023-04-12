package com.example.oldbatch.basic1;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
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

    @Bean(name = "basic1_job")
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }

    @Bean(name = "basic1_step1")
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(
                        new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                                /**
                                 * 두가지 방식으로 파라미터를 뽑아낼 수 있음
                                 */
                                // contribution 에서 추출
                                JobParameters jobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
                                System.out.println(jobParameters.getString("stringKey"));
                                System.out.println(jobParameters.getLong("longKey"));
                                System.out.println( jobParameters.getDate("dateKey"));
                                System.out.println( jobParameters.getDouble("doubleKey"));
                                // chunkContext 에서 추출
                                Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();

                                System.out.println("step1 > tasklet");
                                return RepeatStatus.FINISHED;
                            }
                        }
                )
                .build();
    }

    @Bean(name = "basic1_step2")
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(
                        (contribution, chunkContext) -> {
                            System.out.println("step2 > tasklet");
//                            throw new RuntimeException();
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    @Bean(name = "basic1_step2")
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet(new CustomTasklet())
                .build();
    }
}
