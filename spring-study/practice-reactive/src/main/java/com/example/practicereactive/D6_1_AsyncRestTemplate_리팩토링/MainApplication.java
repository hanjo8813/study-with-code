package com.example.practicereactive.D6_1_AsyncRestTemplate_리팩토링;

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

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
@EnableAsync
public class MainApplication {

    @RestController
    public static class MyController {

        static final String URL1 = "http://localhost:8081/service1?req={req}";
        static final String URL2 = "http://localhost:8081/service2?req={req}";

        @Autowired
        MyService myService;

        @GetMapping("/rest4")
        public DeferredResult<String> rest4(int idx) {
            DeferredResult<String> dr = new DeferredResult<>();
            AsyncRestTemplate rt = new AsyncRestTemplate(new Netty4ClientHttpRequestFactory(new NioEventLoopGroup(1)));

            Completion
                    .from(rt.getForEntity(URL1, String.class, "h" + idx))
                    .andApply(s -> rt.getForEntity(URL2, String.class, s.getBody()))
                    .andApply(s -> myService.work(s.getBody()))
                    .andError(e -> dr.setErrorResult("Error [" + e.toString() + "]"))
                    .andAccept(s -> dr.setResult(s));

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
