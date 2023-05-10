package com.example.studybatchlecture.ex09_flow;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * simple job 내에서 flow 구현하는 방식
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
                .start(flow1())
                    .on("COMPLETED")
                    .to(flow2())
                .end()
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Flow flow1() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow1");
        // SimpleFlow
        return flowBuilder
                .start(step1())
                .next(step2())
                .end();
    }

    @Bean
    public Flow flow2() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow2");
        return flowBuilder
                .start(flow3())
                .next(step5())
                .next(step6())
                .end();
    }

    @Bean
    public Flow flow3() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow3");
        return flowBuilder
                .start(step3())
                .next(step4())
                .end();
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

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet(
                        (contribution, chunkContext) -> {
                            System.out.println("tasklet 4");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5")
                .tasklet(
                        (contribution, chunkContext) -> {
                            System.out.println("tasklet 5");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }

    @Bean
    public Step step6() {
        return stepBuilderFactory.get("step6")
                .tasklet(
                        (contribution, chunkContext) -> {
                            System.out.println("tasklet 6");
                            return RepeatStatus.FINISHED;
                        }
                )
                .build();
    }
}
