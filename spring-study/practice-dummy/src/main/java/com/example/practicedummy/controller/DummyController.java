package com.example.practicedummy.controller;

import com.example.practicedummy.constant.DummyEnum;
import com.example.practicedummy.dto.ResponseDto;
import com.example.practicedummy.service.DummyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DummyController {

    private final DummyService service;

    @GetMapping("/1")
    public void test(){
        service.save();
    }

    @GetMapping("/2")
    public int test2(){
        return 1;
    }

    @GetMapping("/3")
    public ResponseEntity<Integer> test3(){
        return ResponseEntity.ok(1);
    }

    @GetMapping("/4")
    public ResponseEntity<Void> test4(){
        return ResponseEntity.notFound().build();
    }

    /**
     * "pureDt": "2022-08-25T10:13:30.635601",
     * "dt": "2022-08-25T01:41",
     * "t": "01:41:21"
     */
    @GetMapping("/5")
    public ResponseDto test5(){
        return ResponseDto.builder()
                .pureDt(LocalDateTime.now())
                .dt(LocalDateTime.now())
                .t(LocalTime.now())
                .build();
    }

    /**
     * param str -> enum 변환 잘 된다~
     * null 체크 필요
     */
    @GetMapping("/6")
    public void test6(
            @RequestParam(value = "enum", required = false) DummyEnum dummyEnum
    ){
        log.info("{}", dummyEnum);
    }

}