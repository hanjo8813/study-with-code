//package com.example.oldbatch;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@RequiredArgsConstructor
//@Configuration
//public class HelloJobConfig {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job helloJob() {
//        return jobBuilderFactory.get("helloJob")
//                .start(helloStep1())
//                .next(helloStep2())
//                .build();
//    }
//
//    @Bean
//    public Step helloStep1() {
//        return stepBuilderFactory.get("helloStep1")
//                .tasklet(
//                        new Tasklet() {
//                            @Override
//                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                                System.out.println("step1");
//                                return RepeatStatus.FINISHED;
//                            }
//                        }
//                )
//                .build();
//    }
//
//    @Bean
//    public Step helloStep2() {
//        return stepBuilderFactory.get("helloStep2")
//                .tasklet(
//                        (contribution, chunkContext) -> {
//                            System.out.println("step2");
//                            return RepeatStatus.FINISHED;
//                        }
//                )
//                .build();
//    }
//
//}
