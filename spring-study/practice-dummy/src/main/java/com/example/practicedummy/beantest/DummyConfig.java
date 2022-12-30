package com.example.practicedummy.beantest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyConfig {

    @Bean
    public DummyBean dummy(){
        return DummyBean.builder()
                .str("hey")
                .build();
    }
}
