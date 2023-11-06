package com.example.practiceaop.controller;

import com.example.practiceaop.service.TestService2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test/2")
public class TestController2 {

    private final TestService2 service2;

    @GetMapping("/1")
    public void temp1() {
        service2.temp1();
    }

    @GetMapping("/2")
    public void temp2() {
        service2.temp2();
    }
}
