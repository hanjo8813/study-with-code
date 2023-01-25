package com.example.practicereactive.D5_1_;

import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

@SpringBootApplication
@EnableAsync
public class MainApplication {

    @RestController
    public static class MyController {

        static final String URL1 = "http://localhost:8081/service1?req={req}";
        static final String URL2 = "http://localhost:8081/service2?req={req}";

        /**
         * 톰캣 스레드 1개 서버에서 요청 100개를 동시에 받은 후 외부 요청을 해야하는데 그게 2초나 걸린다면?
         * 2 * 100 = 200초 걸림
         * 스레드가 외부에서 응답 받기까지 2초씩 대기해야 하는 상태
         *
         * 그럼 Callable 쓰면 안되나? 생각했는데..
         * Callable은 내부 로직이 오래 걸릴때 워커 스레드를 만드는 방식으로 사용하는것
         * 이건 외부 요청에 의존되기 때문에 이걸로 해결 불가~
         */
        @GetMapping("/rest1")
        public String rest1(int idx) {
            RestTemplate rt = new RestTemplate();
            String res = rt.getForObject(URL1, String.class, "hello" + idx);
            return res;
        }

        /**
         * 2초 걸림
         * 서블릿 3.1 + AsyncRestTemplate 환장의 조합으로 최대의 효율
         *
         * 근데 비밀스럽게 스레드 100개 더 만들어짐
         * 뒤에서 워커 스레드 만들어서 요청날리는것
         * (사실상 동작 방식은 Callable이랑 똑같음 ㅋㅋ)
         *
         * + 원래 ListenableFuture는 콜백 등록해줘야 함
         * but 컨트롤러에서 리턴 때리면 스프링 MVC가 알아서 다 해줌..
         */
        @GetMapping("/rest2")
        public ListenableFuture<ResponseEntity<String>> rest2(int idx) {
            AsyncRestTemplate rt = new AsyncRestTemplate();
            return rt.getForEntity(URL1, String.class, "hello" + idx);
        }

        /**
         * 2초 걸림
         * 스레드도 조금만 추가됨 (!!)
         *
         * CPU 수 * 2개만큼 생성됨
         * 부하에 알맞는 스레드만 만들고 nio로 처리한것 (이게 바로 netty)
         */
        @GetMapping("/rest3")
        public ListenableFuture<ResponseEntity<String>> rest3(int idx) {
            AsyncRestTemplate rt = new AsyncRestTemplate(new Netty4ClientHttpRequestFactory(new NioEventLoopGroup(1)));
            return rt.getForEntity(URL1, String.class, "hello" + idx);
        }


        /**
         * 외부 api -> service1 + service2 = 2 + 2 = 4초 걸림
         *
         * 응답 2차 가공하기
         * 콜백 패턴 + DeferredResult를 사용해 지연됐던 응답을 캐치해 재가공 가능
         *
         * 콜백 중첩해서 사용 가능
         * 3중첩까지 구현함.. 어질어질
         * -> Callback Hell
         */
        @Autowired
        MyService myService;

        @GetMapping("/rest4")
        public DeferredResult<String> rest4(int idx) {

            DeferredResult<String> dr = new DeferredResult<>();
            AsyncRestTemplate rt = new AsyncRestTemplate(new Netty4ClientHttpRequestFactory(new NioEventLoopGroup(1)));
            ListenableFuture<ResponseEntity<String>> f1 = rt.getForEntity(URL1, String.class, "hello" + idx);

            // callback 1
            f1.addCallback(
                    s1 -> {
                        ListenableFuture<ResponseEntity<String>> f2 = rt.getForEntity(URL2, String.class, s1.getBody());

                        // callback 2
                        f2.addCallback(
                                s2 -> {
                                    ListenableFuture<String> f3 = myService.work(s2.getBody());

                                    // callback 3
                                    f3.addCallback(
                                            s3 -> {
                                                dr.setResult(s3);
                                            },
                                            e3 -> {
                                                dr.setErrorResult(e3.getMessage());
                                            }
                                    );
                                },
                                e2 -> {
                                    dr.setErrorResult(e2.getMessage());
                                }
                        );
                    },
                    e1 -> {
                        dr.setErrorResult(e1.getMessage());
                    }
            );

            return dr;
        }
    }

    @Service
    public static class MyService {
        @Async
        public ListenableFuture<String> work(String req) {
            return new AsyncResult<>(req + "/asyncwork");
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
