package com.example.practicedummy.c_엔티티타입;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/c")
@RestController
public class ControllerC {

    private final ServiceC service;

    @GetMapping("/1")
    public void test(){
        service.save();
    }
}
