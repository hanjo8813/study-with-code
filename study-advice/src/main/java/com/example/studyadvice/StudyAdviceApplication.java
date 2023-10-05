package com.example.studyadvice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@ComponentScan(excludeFilters = @Filter(type = FilterType.REGEX, pattern = "com.*.test2.*"))
@SpringBootApplication
public class StudyAdviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyAdviceApplication.class, args);
    }

}
