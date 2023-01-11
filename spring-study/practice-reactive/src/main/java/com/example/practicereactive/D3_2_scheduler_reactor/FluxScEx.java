package com.example.practicereactive.D3_2_scheduler_reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * 스케줄러 위치에 따른 스레드 실행 순서 확인
 * <p>
 * 1. subOn 으로 flow 전체에 sub 스레드 적용
 * 2. 이후 pubOn 아래로 pub 스레드 적용
 *
 * 스케줄러는 user 스레드네
 */
public class FluxScEx {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .publishOn(Schedulers.newSingle("pub"))
                .log()
                .subscribeOn(Schedulers.newSingle("sub"))
                .subscribe(System.out::println);

        System.out.println("exit");
    }
}
