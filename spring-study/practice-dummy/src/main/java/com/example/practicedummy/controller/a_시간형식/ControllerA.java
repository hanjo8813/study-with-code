package com.example.practicedummy.controller.a_시간형식;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;

@RequestMapping("/a")
@RestController
public class ControllerA {

    @GetMapping("/1")
    public ResponseDto test(){
        return ResponseDto.builder()
                .pureDt(LocalDateTime.now())
                .dt(LocalDateTime.now())
                .t(LocalTime.now())
                .build();
    }
}