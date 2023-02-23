package com.example.reactive.controller;

import java.time.LocalDateTime;
import org.springframework.data.redis.core.ReactiveListOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/reactive/redis")
@RestController
public class RedisController {

    private final ReactiveListOperations<String, String> operations;

    public RedisController(ReactiveStringRedisTemplate redisTemplate) {
        operations = redisTemplate.opsForList();
    }

    @GetMapping("/insert")
    public Mono<Long> insertValue() {
        return operations.rightPush("reactive-list", LocalDateTime.now().toString());
    }
}
