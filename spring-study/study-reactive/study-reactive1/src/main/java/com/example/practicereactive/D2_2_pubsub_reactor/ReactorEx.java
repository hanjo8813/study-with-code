package com.example.practicereactive.D2_2_pubsub_reactor;

import reactor.core.publisher.Flux;

/**
 * reactive streams 스펙으로 잘 만들어진 구현체가 Reactor
 * java pub/sub -> reactive streams -> reactor -> RxJava, Webflux ...?
 *
 * Flux == publisher 구현체
 */

public class ReactorEx {
    public static void main(String[] args) {
        Flux.<Integer>create(e -> {
                    e.next(1);
                    e.next(2);
                    e.next(3);
                    e.complete();
                })
                .log()
                .map(s -> s * 10)
                .reduce(0, (a, b) -> a + b)
                .log()
                .subscribe(System.out::println);
    }
}

