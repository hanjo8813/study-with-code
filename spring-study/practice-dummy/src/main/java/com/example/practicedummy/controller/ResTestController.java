package com.example.practicedummy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResTestController {
    @GetMapping
    public int test(){
        return 1;
    }
}
