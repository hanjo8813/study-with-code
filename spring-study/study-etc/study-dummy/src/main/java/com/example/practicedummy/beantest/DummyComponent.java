package com.example.practicedummy.beantest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class DummyComponent {

    private final DummyBean dummyBean;

    @PostConstruct
    public void post1(){
        System.out.println(dummyBean.getStr());
    }

    @PostConstruct
    public void post2(){
        System.out.println("222222");
    }
}
