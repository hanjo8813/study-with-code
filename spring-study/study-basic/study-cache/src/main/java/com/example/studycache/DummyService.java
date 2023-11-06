package com.example.studycache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DummyService {

    private final DummyRepository repository;

    @Cacheable(cacheNames = "dummy")
    public Dummy getDummy() {
        System.out.println("call service");
        return repository.getDummy();
    }

    @Cacheable(cacheNames = "dummy2")
    public Dummy getDummy2() {
        System.out.println("call service2");
        System.out.println(getDummy());
        return new Dummy(2, "B");
    }

}
