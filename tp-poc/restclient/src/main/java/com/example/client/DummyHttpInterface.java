package com.example.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@Component
@HttpExchange
public interface DummyHttpInterface {

    @GetExchange("/get/{id}")
    Dummy getDummy(@PathVariable int id, @RequestParam int param, @RequestBody String body);
}
