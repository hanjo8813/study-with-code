package com.example.practicereactive.D4_3_future_spring_web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * memo.md 참고 ㄱ
 *
 * - 얫날 servlet
 * 1 커넥션 == 1 스레드
 * 이 방식은 비동기 처리할때 문제 유발함
 *
 * - servlet 3.0
 * 비동기 서블릿
 * http 커넥션은 NIO로 처리
 * but req/res 처리는 blocking (input/outputStream 사용해서 구현되어 있기 때문)
 *
 * - servlet 3.1
 * req/res 처리까지 NIO
 * 콜백 패턴도 사용 가능~
 *
 */
@Slf4j
@EnableAsync
@SpringBootApplication
public class Application {

    @RestController
    public static class MyController {
        /**
         * 동기
         */
        @GetMapping("/sync")
        public String sync() throws InterruptedException {
            log.info("async");
            Thread.sleep(2000);
            return "hello";
        }

        /**
         * 비동기
         * Callable 객체를 그냥 리턴해도 스프링이 알아서 처리해줌
         * (실행 및 응답까지)
         */
        @GetMapping("/async")
        public Callable<String> async() throws InterruptedException {
            log.info("callable");
            return () -> {
                log.info("async");
                Thread.sleep(2000);
                return "hello";
            };
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
