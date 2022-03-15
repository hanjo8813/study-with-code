package com.example.practiceaop;


import com.example.practiceaop.controller.TestController;
import com.example.practiceaop.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@RequiredArgsConstructor
@SpringBootApplication
public class PracticeAopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PracticeAopApplication.class, args);
    }

    private final ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(applicationContext.getBean(TestService.class).getClass());
        System.out.println(applicationContext.getBean(TestController.class).getClass());
    }
}
