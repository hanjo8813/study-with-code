package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class DummyController {

    @GetMapping("/get/{id}")
    public Dummy getMethod(@PathVariable int id, @RequestParam int param, @RequestBody String body) {
        System.out.println(id);
        System.out.println(param);
        System.out.println(body);
        return Dummy.of(9999, "good");
    }
}
