//package com.example.studybatchlecture.test.arch;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.core.task.TaskExecutor;
//
//@Configuration
//@RequiredArgsConstructor
//public class JobConfig {
//
//    private final JobRepository jobRepository;
//
//    @Bean(name = "alarmJobLauncher")
//    public JobLauncher jobLauncher() {
//        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
//        simpleJobLauncher.setJobRepository(jobRepository);
//        simpleJobLauncher.setTaskExecutor(taskExecutor());
//        return simpleJobLauncher;
//    }
//
//    @Bean(name = "alarmTaskExecutor")
//    public TaskExecutor taskExecutor() {
//        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
//        simpleAsyncTaskExecutor.setConcurrencyLimit(5);
//        return simpleAsyncTaskExecutor;
//    }
//}
