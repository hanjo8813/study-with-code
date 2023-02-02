package com.example.practicereactive.D7_1_CompletableFuture;

import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@EnableAsync
public class MainApplication {

    @RestController
    public static class MyController {

        static final String URL1 = "http://localhost:8081/service1?req={req}";
        static final String URL2 = "http://localhost:8081/service2?req={req}";

        @Autowired
        MyService myService;

        /**
         * 콜백헬 코드를 CompletableFuture로 리팩토링하기
         *
         * exceptionally 인자값은 Function 이라서 throw 만 하고 끝내지 못함 -> 억지 리턴값 만들어줌
         * thenApplyAsync 써서 CF 자체적으로 비동기 적용 (워커 스레드 만들기 + Compose 타입변환도 필요없음)
         */
        @GetMapping("/rest")
        public DeferredResult<String> rest(int idx) {
            DeferredResult<String> dr = new DeferredResult<>();
            AsyncRestTemplate rt = new AsyncRestTemplate(new Netty4ClientHttpRequestFactory(new NioEventLoopGroup(1)));

            toCF(rt.getForEntity(URL1, String.class, "hello" + idx))
                    .thenCompose(
                            s -> {
                                if (1==1) throw new RuntimeException("ERROR");
                                return toCF(rt.getForEntity(URL2, String.class, s.getBody()));
                            }
                    )
                    .thenApplyAsync(s2 -> myService.work(s2.getBody()))
                    .thenAccept(s3 -> dr.setResult(s3))
                    .exceptionally(e -> {
                        dr.setErrorResult(e.getMessage());
                        return (Void) null;
                    });

            return dr;
        }

        /**
         * AsyncRestTemplate 응답값으로 CompletableFuture 가 없음
         * 따라서 LF -> CF 로 convert 하는 메소드를 만들어줌
         */
        <T> CompletableFuture<T> toCF(ListenableFuture<T> lf) {
            CompletableFuture<T> cf = new CompletableFuture<T>();
            lf.addCallback(
                    s -> cf.complete(s),
                    e -> cf.completeExceptionally(e)
            );
            return cf;
        }
    }

    // ----------------------------------------------------------------------------------------------------

    /**
     * 기존에 @Async 붙여서 비동기로 실행하던 코드에서 비동기 없애버림
     */
    @Service
    public static class MyService {
        public String work(String req) {
            return req + "/asyncwork";
        }
    }

    @Bean
    ThreadPoolTaskExecutor myThreadPool() {
        ThreadPoolTaskExecutor te = new ThreadPoolTaskExecutor();
        te.setCorePoolSize(1);
        te.setMaxPoolSize(1);
        te.initialize();
        return te;
    }

    public static void main(String[] args) {
        System.setProperty("server.tomcat.max-threads", "1");
        SpringApplication.run(MainApplication.class, args);
    }
}