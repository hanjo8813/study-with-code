package com.example.mvc.controller;

import com.example.mvc.document.MongoDummy;
import com.example.mvc.repository.MongoDummyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/mvc/mongo")
@RestController
public class MongoController {

    private final MongoDummyRepository repository;

    @GetMapping("/insert")
    public void insert() {
        MongoDummy dummy = MongoDummy.builder().num(1).str("a").build();
        repository.save(dummy);
    }
}
