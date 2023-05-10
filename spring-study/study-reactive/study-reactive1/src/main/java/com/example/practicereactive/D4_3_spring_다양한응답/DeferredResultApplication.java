package com.example.practicereactive.D4_3_spring_다양한응답;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * DeferredResult : 지연된 http 응답을 나중에 처리해주는 놈
 * ex) 채팅방 같은 경우 사용 가능
 *
 * 예시의 동작 흐름은 memo.md 참고
 *
 * 워커 스레드가 사용되지 않음
 * 이벤트성 구조에서 유용함
 * 비동기 호출할때 유용
 */
@Slf4j
@EnableAsync
@SpringBootApplication
public class DeferredResultApplication {

    @RestController
    public static class MyController {
        Queue<DeferredResult<String>> results = new ConcurrentLinkedDeque<>();

        @GetMapping("/dr")
        public DeferredResult<String> dr() {
            log.info("dr");
            DeferredResult<String> dr = new DeferredResult<>(600000L);
            results.add(dr);
            return dr;
        }

        @GetMapping("/dr/count")
        public String drcount() {
            return String.valueOf(results.size());
        }

        @GetMapping("/dr/event")
        public String drevent(String msg) {
            for (DeferredResult<String> dr : results) {
                dr.setResult("Hello " + msg);
                results.remove(dr);
            }
            return "OK";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DeferredResultApplication.class, args);
    }
}
