package com.example.practicereactive2.D8_2_개인테스트;

import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class Test {

    /**
     * - 응답을 가져오는 메소드로 retrieve 라는 놈도 있고, 얘랑 비교가 많이 된다.
     * retrieve : 응답을 바로 꺼내서 접근이 가능 / 바로 response body 처리
     * exchange : 체이닝 메소드를 통해 람다로 접근해야함 / 응답에 대한 디테일한 컨트롤이 가능
     *
     * - exchange는 왜 deprecated 되었는가?
     * 응답을 소비하지 consume하지 않으면 memory or connection leak 발생할 수 있다.
     * 참고 : https://yangbongsoo.tistory.com/9
     *
     */
    public static void main(String[] args) {
        WebClient client = WebClient.create();

        Mono<ClientResponse> exchange = client.get().uri("").exchange();
        Mono<String> r1 = exchange.flatMap(c -> c.bodyToMono(String.class));


        WebClient.ResponseSpec retrieve = client.get().uri("").retrieve();
        Mono<String> r2 = retrieve.bodyToMono(String.class);

    }
}
