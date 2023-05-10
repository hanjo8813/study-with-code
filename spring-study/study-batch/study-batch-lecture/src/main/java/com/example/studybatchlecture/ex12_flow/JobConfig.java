package com.example.studybatchlecture.ex12_flow;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * decider 예제
 * step listener 등록해서 ExitStatus 조작 필요 없이 분기
 */
@RequiredArgsConstructor
@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomJobExecutionDecider decider;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .next(decider)
                .from(decider)
                    .on("O")
                    .to(step2())
                .from(decider)
                    .on("X")
                    .to(step3())
                .end()
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(
                        (contribution, chunkContext) -> {
                            System.out.println("tasklet 1");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(
                        (contribution, chunkContext) -> {
                            System.out.println("tasklet 2");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet(
                        (contribution, chunkContext) -> {
                            System.out.println("tasklet 3");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

}