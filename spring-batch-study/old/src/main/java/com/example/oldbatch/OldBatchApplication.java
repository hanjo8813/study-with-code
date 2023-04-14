package com.example.oldbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication(scanBasePackages = {"com.example.oldbatch.basic3"})
public class OldBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(OldBatchApplication.class, args);
    }

}
