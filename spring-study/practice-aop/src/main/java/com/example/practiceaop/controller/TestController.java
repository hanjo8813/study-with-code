package com.example.practiceaop.controller;

import com.example.practiceaop.service.TestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestServiceImpl testService;

    /**
     * Controller -> Annotation -> Service 모든 AOP JoinPoint 출력
     */
    @GetMapping("/test1")
    public String test1(){
        return testService.test1();
    }

    /**
     * Controller aspect 만 출력
     */
    @GetMapping("/test2")
    public String test2(){
        return testService.test2();
    }

    /**
     * AfterThrowing 출력
     */
    @GetMapping("/test3")
    public String test3(){
        return testService.test3();
    }
}
