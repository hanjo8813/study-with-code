package com.example.practiceaop.service;

import com.example.practiceaop.aop.DoSomething;
import com.example.practiceaop.aop.DoSomething2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService2 {

    private final ApplicationContext context;

    @DoSomething
    public void temp1() {
        System.out.println("temp1");
    }

    @DoSomething
    public void temp2() {
        System.out.println("temp2");

//        temp1();

        TestService2 proxy = context.getBean(TestService2.class);
        proxy.temp1();
    }

}
