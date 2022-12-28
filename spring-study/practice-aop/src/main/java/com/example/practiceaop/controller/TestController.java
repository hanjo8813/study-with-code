package com.example.practiceaop.controller;

import com.example.practiceaop.aop.DoSomething;
import com.example.practiceaop.aop.DoSomething2;
import com.example.practiceaop.service.TestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestServiceImpl testService;

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
        return testService.test3();
    }

    @GetMapping("/test4")
    private String test4() {
        System.out.println("ddd");
        return "";
    }

    @GetMapping("/test5")
    public String test5() {
        temp();
        return "";
    }

    @DoSomething
    public void temp(){
        System.out.println("dddd");
    }

    @DoSomething2(str = "hi", num = 0)
    @GetMapping("/test6")
    public String test6(
            @RequestParam("str") String str,
            @RequestParam("num") int num
    ){
        return "";
    }

    // test7("hi", 10);


    @DoSomething2(params="#title.concat(#age)")
    @GetMapping("/test7")
    @Cacheable(key = "#title.concat(#age)")
    public String test7(String title, Integer age){
        return "";
    }
}
