package com.example.oldbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication(scanBasePackages = {"com.example.oldbatch.ex10_flow"})
public class OldBatchApplication {

    public static void main(String[] args) {
        System.setProperty("spring.batch.job.enabled", "true");
        SpringApplication.run(OldBatchApplication.class, args);
    }

}
