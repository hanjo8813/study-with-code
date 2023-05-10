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
}
