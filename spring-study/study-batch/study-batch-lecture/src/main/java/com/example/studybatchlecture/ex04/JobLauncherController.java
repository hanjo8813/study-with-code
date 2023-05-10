package com.example.studybatchlecture.ex04;//package com.example.oldbatch.basic4;
//
//import java.util.Date;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class JobLauncherController {
//
//    private final JobConfig jobConfig;
//    private final JobLauncher jobLauncher;
//    private final BasicBatchConfigurer basicBatchConfigurer;
//
//    @GetMapping("/async")
//    public void async() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//        SimpleJobLauncher simpleJobLauncher = (SimpleJobLauncher)basicBatchConfigurer.getJobLauncher();
//        simpleJobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        jobLauncher.run(jobConfig.job(), new JobParametersBuilder().addDate("dateKey", new Date()).toJobParameters());
//    }
//}
