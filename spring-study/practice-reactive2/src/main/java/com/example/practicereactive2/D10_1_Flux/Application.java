package com.example.practicereactive2.D10_1_Flux;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestController
@SpringBootApplication
public class Application {

    @GetMapping("/event/{id}")
    Mono<List<Event>> event(@PathVariable long id) {
        List<Event> list = Arrays.asList(new Event(1L, "event1"), new Event(2L, "event2"));
        return Mono.just(list);
    }

    /**
     * flux 사용해서 여러 객체 리턴하기
     */
    @GetMapping("/events1")
    Flux<Event> events1() {
        return Flux.just(new Event(1L, "event1"), new Event(2L, "event2"));
    }

    /**
     * List -> Flux
     * http 스트림 사용해서 데이터를 하나씩 보낸다
     */
    @GetMapping(value = "/events2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Event> events2() {
        List<Event> list = Arrays.asList(new Event(1L, "event1"), new Event(2L, "event2"));
        return Flux.fromIterable(list);
    }

    /**
     * Stream -> Flux
     * 스트림으로 데이터 생성 -> 1초 간격으로 생성 -> 10개까지만 생성
     * server sent event?
     */
    @GetMapping(value = "/events3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Event> events3() {
        return Flux
            .fromStream(Stream.generate(() -> new Event(System.currentTimeMillis(), "value")))
            .delayElements(Duration.ofSeconds(1))
            .take(10);
    }

    /**
     * 데이터 생성 Flux의 generate(Consumer) 사용
     * Consumer : sink / 데이터 흘려보낸다는 뜻 / next 호출 가능
     */
    @GetMapping(value = "/events4", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Event> events4() {
        return Flux
            .<Event>generate(sink -> sink.next(new Event(System.currentTimeMillis(), "value")))
            .delayElements(Duration.ofSeconds(1))
            .take(10);
    }

    /**
     * generate(Callable, BiFunctional) 사용하기
     * Callable(Consumer) : 상태값 정의
     * BiFunctional : 상태값 + sink 사용
     */
    @GetMapping(value = "/events5", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Event> events5() {
        return Flux
            .<Event, Long>generate(
                () -> 1L,
                (id, sink) -> {
                    sink.next(new Event(id, "value" + id));
                    return id + 1;
                }
            )
            .delayElements(Duration.ofSeconds(1))
            .take(10);
    }

    /**
     * interval : 0부터 시작해서 1씩 증가. 증가 주기를 넣어줌
     * zip : Flux 조합하기. 사이좋게 실행
     * ㄴ f1 체인1 -> f2 체인1 -> f1 체인2 -> f2 체인2 -> ....
     */
    @GetMapping(value = "/events6", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Event> events6() {
        Flux<Event> f1 = Flux
            .<Event, Long>generate(
                () -> 1L,
                (id, sink) -> {
                    sink.next(new Event(id, "value" + id));
                    return id + 1;
                }
            );
        Flux<Long> f2 = Flux.interval(Duration.ofSeconds(1));

        return Flux
            .zip(f1, f2)
            .map(tuple -> tuple.getT1())
            .take(10);
    }
    // -----------------------------------------------------------------------------

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Data
    @AllArgsConstructor
    public static class Event {
        long id;
        String value;
    }
}
