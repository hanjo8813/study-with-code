package com.example.practicedummy.controllertest.basic.service;

import com.example.practicedummy.controllertest.basic.repository.DummyRepository;
import com.example.practicedummy.controllertest.basic.Dummy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class DummyService {

    private final DummyRepository repository;

    public Dummy save(){
        Dummy dummyC = Dummy.builder()
                .bool(true)
                .build();
        log.info("{}", dummyC);

        Dummy save = repository.save(dummyC);
        log.info("{}", save);

        return save;
    }

}
