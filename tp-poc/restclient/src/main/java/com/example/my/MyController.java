package com.example.my;

import com.example.client.Dummy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyController {

    private final MyService service;

    @GetMapping
    public Dummy myTest() {
        return service.getDummy();
    }
}
