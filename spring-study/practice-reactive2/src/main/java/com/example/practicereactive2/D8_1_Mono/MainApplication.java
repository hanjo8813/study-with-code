package com.example.practicereactive2.D8_1_Mono;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * 실습 방식 자체는 이전 강의와 동일함
 */
@Slf4j
@SpringBootApplication
@EnableAsync
public class MainApplication {

    @RestController
    public static class MyController {

        static final String URL1 = "http://localhost:8081/service1?req={req}";
        static final String URL2 = "http://localhost:8081/service2?req={req}";

        @Autowired
        MyService myService;

        WebClient client = WebClient.create();

        /**
         * Mono는 publisher라고 생각하면됨.
         * sub은 스프함이 알아서 제어 (D2_3_pubsub_spring 예제처럼)
         * > 결론적으로 AsyncRestTemplate + DeferredResult 방식과 똑같이 동작함
         *
         * !!! 매우 헷갈림 !!!
         * WebClient의 응답 타입은 항상 Mono<ClientResponse> 이렇게 실제 응닶값이 Mono로 wrapping되어 있음
         * 따라서 실제 값에 접근하기 위해 map or flatMap을 사용해야 함.
         * ㄴ Mono<...> 내부 타입을 뽑아 람다 파라미터로 받고, 리턴시 다시 Mono로 wrapping 해준다.
         *
         * 이후 bodyToMono를 통해 값을 꺼냄 (그냥 body로 꺼내면 되지 않나?)
         *
         * 만약 map을 사용했다면... map 리턴 타입 Mono + bodyToMono의 Mono
         * ㄴ 최종 리턴 값이 Mono<Mono<...>> 이런식으로 Mono로 두번 감싸진 형태가 된다.
         *
         * 따라서 이런 경우에 체이닝하려면 flatMap으로 한꺼풀 벗겨줘야함
         */
        @GetMapping("/rest")
        public Mono<String> rest(int idx) {
//            Mono.just("hello")    // of라고 생각하믄 됨

//            Mono<ClientResponse> res = client.get().uri(URL1, idx).exchange();
//            Mono<Mono<String>> map = res.map(clientResponse -> clientResponse.bodyToMono(String.class));

            return client.get().uri(URL1, idx).exchange()
                        .flatMap(c -> c.bodyToMono(String.class))
                        .doOnNext(str -> log.info(str))
                    .flatMap((String res1) -> client.get().uri(URL2, res1).exchange())
                        .flatMap(c -> c.bodyToMono((String.class)))
                        .doOnNext(str -> log.info(str))
                    .flatMap(res2 -> Mono.fromCompletionStage(myService.work(res2)))
                        .doOnNext(str -> log.info(str));
        }
    }

    // ----------------------------------------------------------------------------------------------------

    /**
     * 서비스 메소드도 비동기로 동작하게 하기 위해 @Async
     * 리턴은 ListenableFuture or CompletableFuture 둘 다 써도 되지만...
     * 콜백 등록하기 귀찮으니 걍 CompletableFuture 완료됐다 해버리기
     */
    @Service
    public static class MyService {
        @Async
        public CompletableFuture<String> work(String req) {
            return CompletableFuture.completedFuture(req + "/asyncwork");
        }
    }

    public static void main(String[] args) {
        System.setProperty("reactor.ipc.netty.workerCount", "1");
        System.setProperty("reactor.ipc.netty.pool.maxConnections", "2000");
        SpringApplication.run(MainApplication.class, args);
    }
}
