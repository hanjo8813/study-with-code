package com.example.studycache;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DummyController {

    private final DummyService service;

    @GetMapping("/dummy")
    public Dummy getDummy() {
        System.out.println("call controller");
        return service.getDummy();
    }
}
