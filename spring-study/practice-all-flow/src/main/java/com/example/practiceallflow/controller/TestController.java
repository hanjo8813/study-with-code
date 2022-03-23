package com.example.practiceallflow.controller;

import com.example.practiceallflow.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public String testController() {
        return testService.testService();
    }
}
