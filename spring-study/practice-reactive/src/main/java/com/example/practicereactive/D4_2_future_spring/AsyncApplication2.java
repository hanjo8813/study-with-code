package com.example.practicereactive.D4_2_future_spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * 쌩 Future 방식을 콜백 패턴으로 변경하기
 * 스프링에서 제공하는 ListenableFuture 사용하면 콜백 패턴 적용 가능함
 */
@Slf4j
@EnableAsync
@SpringBootApplication
public class AsyncApplication2 {

    @Component
    public static class MyService {
        @Async(value = "tp")
        public ListenableFuture<String> hello() throws InterruptedException {
            log.info("hello()");
            Thread.sleep(2000);
            return new AsyncResult<>("Hello");
        }
    }

    /**
     * Async 사용시 반드시 스레드풀 설정해줘야함 -> 안해주면 제한없이 스레드 무한 생성해버림..
     *
     * Executor 관련 객체 반환하는 bean 있으면 그 설정으로 등록됨
     * 해당 메소드명으로 @Async value 매핑 가능
     *
     * core 사이즈 만큼 시작 -> core 꽉 차면 -> queue 사이즈만큼 줄세우고 -> queue도 꽉차면 -> 그때 max pool size 까지 스레드 생성
     */
    @Bean
    ThreadPoolTaskExecutor tp() {
        ThreadPoolTaskExecutor te = new ThreadPoolTaskExecutor();
        te.setCorePoolSize(10);
        te.setMaxPoolSize(100);
        te.setQueueCapacity(200);
        te.setThreadNamePrefix("mythread");
        te.initialize();
        return te;
    }

    public static void main(String[] args) {
        try (ConfigurableApplicationContext c = SpringApplication.run(AsyncApplication2.class, args)) {
        }
    }

    @Autowired
    MyService myService;

    @Bean
    ApplicationRunner run2() {
        return args -> {
            log.info("run()");
            ListenableFuture<String> f = myService.hello();
            f.addCallback(
                    s -> System.out.println(s), // 성공시
                    e -> System.out.println(e.getMessage()) // 실패시
            );
        };
    }
}
