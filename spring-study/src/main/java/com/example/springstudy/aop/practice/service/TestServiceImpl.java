package com.example.springstudy.aop.practice.service;

import com.example.springstudy.aop.practice.aop.DoSomething;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{

    @DoSomething    // AOP custom 어노테이션은 외부에서 호출될때만 실행됐음..
    @Override
    public String test1() {
        return "test1";
    }

    @Override
    public String test2() {
        return "test2";
    }

    @Override
    public String test3() {
        throw new RuntimeException("test3");
    }
}
