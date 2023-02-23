package com.example.mvc.controller;

import java.time.LocalDateTime;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mvc/redis")
@RestController
public class RedisController {

    private final ListOperations<String, String> operations;

    public RedisController(StringRedisTemplate redisTemplate) {
        operations = redisTemplate.opsForList();
    }

    @GetMapping("/insert")
    public void insertValue() {
        operations.rightPush("mvc-list", LocalDateTime.now().toString());
    }
}
