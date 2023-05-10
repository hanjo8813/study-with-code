package com.example.practicereactive2.D9_1_Mono;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@SpringBootApplication
public class Application {

    /**
     * just : 간단하게 Mono 객체 만들기.
     */
    @GetMapping("/1")
    Mono<String> temp1() {
        return Mono.just("Hello WebFlux").log();
    }

    @GetMapping("/2")
    Mono<String> temp2() {
        log.info("pos1");
        Mono m = Mono.just(generateHello()).doOnNext(c->log.info(c)).log();
        log.info("pos2");
        return m;
    }

    /**
     * 명령형 vs 선언형
     * 람다를 넣어줘서 콜백 방식으로 비동기 작업할 수 있도록..
     *
     * cold : sub 할때마다 새로운 데이터 생성
     * hot : sub 전에 데이터 생성 혹은 생성해놓은 데이터 재사용..
     */
    @GetMapping("/3")
    Mono<String> temp3() {
        log.info("pos1");
        Mono m = Mono.fromSupplier(() -> generateHello()).doOnNext(c->log.info(c)).log();
        m.subscribe();
        log.info("pos2");
        return m;
    }

    /**
     * block
     * Mono 컨테이너 안의 객체 꺼내보기
     * 이름 그대로 block 코드 아래로는 blocking 시킨다.
     * (뭔가 Future get과 비슷한 느낌)
     * 최대한 사용은 지양하자.
     */
    @GetMapping("/4")
    Mono<String> temp4() {
        log.info("pos1");
        Mono<String> m = Mono.just(generateHello()).doOnNext(c->log.info(c)).log();
        String msg2 = m.block();
        log.info("pos2 : " + msg2);
        return m;
    }

    // -----------------------------------------------------------------------------

    private String generateHello() {
        log.info("method generateHello()");
        return "Hello Mono";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
