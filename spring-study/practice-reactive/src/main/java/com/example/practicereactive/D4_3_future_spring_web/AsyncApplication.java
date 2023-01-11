package com.example.practicereactive.D4_3_future_spring_web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * - 얫날 servlet
 * 1 커넥션 == 1 스레드
 * 이 방식은 비동기 처리할때 문제 유발함
 * (그림이 필요)
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
public class AsyncApplication {

    @RestController
    public static class MyController {
        @GetMapping("/callable")
        public Callable<String> callable() throws InterruptedException {
            log.info("callable");
            return () -> {
                log.info("async");
                Thread.sleep(2000);
                return "hello";
            };
        }
//        public String callable() throws InterruptedException {
//            log.info("async");
//            Thread.sleep(2000);
//            return "hello";
//        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }
}
