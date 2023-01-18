package com.example.practicereactive.D4_3_future_spring_web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.concurrent.Executors;

/**
 * Emitter
 * 한번에 응답을 내려주는게 아닌 스트리밍 방식으로 응답을 나눠서 보내는 방식
 */
@Slf4j
@EnableAsync
@SpringBootApplication
public class EmitterApplication {

    @RestController
    public static class MyController {

        @GetMapping("/emitter")
        public ResponseBodyEmitter emitter() {
            ResponseBodyEmitter emitter = new ResponseBodyEmitter();

            Executors.newSingleThreadExecutor().submit(() -> {
                for (int i = 1; i <= 50; i++) {
                    try {
                        emitter.send("<p>Stream " + i + "</p>");
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }
            });

            return emitter;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(EmitterApplication.class, args);
    }
}
