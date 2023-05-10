package com.example.studybatchlecture.ex10_flow;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FlowJob의 기본적인 Transition을 익히는 예제
 * 이미지 참고
 */
@RequiredArgsConstructor
@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                    .on("FAILED")
                    .to(step2())
                        .on("*")
                        .stop()
                .from(step1())
                    .on("*")
                    .to(step3())
                    .next(step4())
                        .on("FAILED")
                        .end()
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
//                            contribution.setExitStatus(ExitStatus.FAILED);
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

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet(
                        (contribution, chunkContext) -> {
                            System.out.println("tasklet 4");
                            contribution.setExitStatus(ExitStatus.FAILED);
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }
}
