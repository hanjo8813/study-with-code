package com.example.studyprom.counter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/counter")
@RequiredArgsConstructor
@RestController
public class CounterController {

    private final CountService service;

    @GetMapping("/1")
    public void counter1() {
        service.counter1();
    }

    @GetMapping("/2")
    public void counter2() {
        service.counter2();
    }

    @GetMapping("/3/{label}")
    public void counter3(@PathVariable String label) {
        service.counter3(label);
    }

    @GetMapping("/4")
    public void counter4() {
        service.counter4();
    }
}
