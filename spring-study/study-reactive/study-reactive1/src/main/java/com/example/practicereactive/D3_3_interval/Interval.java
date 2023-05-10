package com.example.practicereactive.D3_3_interval;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * user 스레드가 하나라도 있으면 jvm이 강제 종료 X
 * but user 스레드 없이 daemon 스레드가 남아있는 경우엔 걍 종료
 */
@Slf4j
public class Interval {

    /**
     * interval은 daemon 스레드 만듬 -> jvm이 그냥 종료시켜버림
     */
    public static void temp1() throws InterruptedException {
        Flux.interval(Duration.ofMillis(500))
                .subscribe(s -> log.debug("onNext:{}", s));
        log.debug("exit");
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * executor는 user 스레드
     */
    public static void temp2() {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            System.out.println("Hello");
        });
        System.out.println("exit");
    }

    /**
     * take : 데이터 수 정해서 받기
     */
    public static void temp3() throws InterruptedException {
        Flux.interval(Duration.ofMillis(200))
                .take(10)
                .subscribe(s -> log.debug("onNext:{}", s));
        TimeUnit.SECONDS.sleep(10);
    }

    public static void main(String[] args) throws InterruptedException {
//        temp1();
//        temp2();
        temp3();
    }
}
