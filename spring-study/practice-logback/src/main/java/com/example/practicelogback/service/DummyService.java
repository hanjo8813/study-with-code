package com.example.practicelogback.service;

import org.springframework.stereotype.Service;

@Service
public class DummyService {

    public void error(){
        throw new RuntimeException();
    }
}
