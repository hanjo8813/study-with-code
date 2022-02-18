package com.example.springlogback.controller;

import com.example.springlogback.service.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class DummyController {

    private final DummyService dummyService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/test")
    public void test(){
        dummyService.error();
    }

}
