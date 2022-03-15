package com.example.springstudy.aop.practice;

import com.example.springstudy.aop.practice.controller.TestController;
import com.example.springstudy.aop.practice.service.TestService;
import com.example.springstudy.aop.practice.service.TestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@RequiredArgsConstructor
@SpringBootApplication
public class SpringAopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

    private final ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(applicationContext.getBean(TestService.class).getClass());
        System.out.println(applicationContext.getBean(TestController.class).getClass());
    }
}
