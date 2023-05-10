package com.example.practicedummy.beantest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class DummyComponent {

    private final DummyBean dummyBean;

    @PostConstruct
    public void post(){
        System.out.println(dummyBean.getStr());
    }
}
