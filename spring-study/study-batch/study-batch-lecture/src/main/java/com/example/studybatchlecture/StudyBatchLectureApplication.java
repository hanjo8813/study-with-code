package com.example.studybatchlecture;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication(scanBasePackages = {"com.example.studybatchlecture.ex07_step"})
public class StudyBatchLectureApplication {

    public static void main(String[] args) {
        System.setProperty("spring.batch.job.enabled", "true");
        SpringApplication.run(StudyBatchLectureApplication.class, args);
    }

}
