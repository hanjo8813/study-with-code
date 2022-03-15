package com.example.springstudy.aop.practice.controller;

import com.example.springstudy.aop.practice.service.TestService;
import com.example.springstudy.aop.practice.service.TestService2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;
    private final TestService2 testService2;

    @GetMapping("/test1")
    public String test1(){
        return testService.test1();
    }

    @GetMapping("/test2")
    public String test2(){
        return testService.test2();
    }

    @GetMapping("/test3")
    public String test3(){
        return testService2.test3();
    }
}
