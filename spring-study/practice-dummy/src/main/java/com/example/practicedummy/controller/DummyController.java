package com.example.practicedummy.controller;

import com.example.practicedummy.constant.DummyEnum;
import com.example.practicedummy.dto.RequestParamDto;
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
    public void test() {
        service.save();
    }

    @GetMapping("/2")
    public int test2() {
        return 1;
    }

    @GetMapping("/3")
    public ResponseEntity<Integer> test3() {
        return ResponseEntity.ok(1);
    }

    @GetMapping("/4")
    public ResponseEntity<Void> test4() {
        return ResponseEntity.notFound().build();
    }

    /**
     * "pureDt": "2022-08-25T10:13:30.635601",
     * "dt": "2022-08-25T01:41",
     * "t": "01:41:21"
     */
    @GetMapping("/5")
    public ResponseDto test5() {
        return ResponseDto.builder()
                .pureDt(LocalDateTime.now())
                .dt(LocalDateTime.now())
                .t(LocalTime.now())
                .build();
    }

    /**
     * 문제 : String param 받아서 enum 자동변환 되는지
     * 결과 : 잘된다. null 체크 필요
     */
    @GetMapping("/6")
    public void test6(
            @RequestParam(value = "enum", required = false) DummyEnum dummyEnum,
            @RequestParam("essential") String essential,
            @RequestParam("num") int num
    ) {
        log.info("{}", dummyEnum);
    }

    /**
     * 문제 : react에서 null값이 param으로 어떻게 날라오는지
     * 결과 :
     */
    @GetMapping("/7")
    public String test7(
            @RequestParam(value = "temp", required = false) String temp
    ) {
        if (temp == null) {
            System.out.println("null 맞음");
        }
        log.info("{}", temp);
        return "good";
    }

    /**
     * 문제 : model attribute로 파라미터를 받을 때 validation
     * 결과 :
     */
    @GetMapping("/8")
    public void test8(RequestParamDto param) {
        log.info("{}", param.getNum());
        log.info("{}", param.getStr());
    }

}