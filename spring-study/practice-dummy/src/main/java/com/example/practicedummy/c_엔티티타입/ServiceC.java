package com.example.practicedummy.c_엔티티타입;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ServiceC {

    private final DummyRepositoryC repository;

    public DummyC save(){
        DummyC dummyC = DummyC.builder()
                .bool(true)
                .build();
        log.info("{}", dummyC);

        DummyC save = repository.save(dummyC);
        log.info("{}", save);

        return save;
    }

}
