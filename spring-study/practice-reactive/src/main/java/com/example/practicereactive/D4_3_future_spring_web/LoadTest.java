package com.example.practicereactive.D4_3_future_spring_web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AsyncApplication 에 요청 보내고 결과 확인
 * (java visual VM 으로 스레드 확인)
 */

/**
 * - 환경
 * 동기/비동기 여부에 따른 요청 처리 속도 확인
 * client : 100번의 동시요첨 (비동기)
 * server : 동기/비동기 컨트롤러 교체 + 톰캣 스레드 수(default 200) 바꿔가며 테스트
 *
 * - 결과
 * 요청 100 / 동기 컨트롤러 / 스레드 200
 *      ㄴ 소요시간 : 2초
 *      ㄴ 톰캣 스레드 : 100개
 *      ㄴ 분석 : 요청 하나당 2초인것
 * 요청 100 / 동기 컨트롤러 / 스레드 20
 *      ㄴ 소요시간 : 10초
 *      ㄴ 톰캣 스레드 : 20개
 *      ㄴ 분석 : 2초짜리 요청 100개를 동시에 20개밖에 처리 못함 -> 5배 걸림
 * 요청 100 / 비동기 컨트롤러 / 스레드 20
 *      ㄴ 소요시간 : 2초
 *      ㄴ 톰캣 스레드 : 20개
 *      ㄴ 분석 : 요청에 비해 스레드가 부족함에도 커넥션을 nio로 처리했기 때문에 스레드를 알차게 씀
 * 요청 100 / 비동기 컨트롤러 / 스레드 1
 *      ㄴ 소요시간 : 2초
 *      ㄴ 톰캣 스레드 : 1개
 *      ㄴ 분석 : 스레드 1개임에도 성능 쩐다
 *
 * - 결론
 * 워커 스레드는 요청만큼 생성됨
 * but 커넥션을 처리할 수 있는 스레드를 낭비없이 매우 알차게 사용 가능
 */

/**
 * - 환경
 * DeferredResult 사용된 환경
 * client : 100번 동시요청
 * server : /dr에다가 DeferredResult로 받아서 응답 대기상태로 만들고 -> /dr/event로 한번에 처리하기
 */
@Slf4j
public class LoadTest {
    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(100);
        RestTemplate rt = new RestTemplate();
//        String url = "http://localhost:8080/sync";
//        String url = "http://localhost:8080/async";
        String url = "http://localhost:8080/dr";

        StopWatch main = new StopWatch();
        main.start();

        for (int i = 0; i < 100; i++) {
            es.execute(() -> {
                int idx = counter.addAndGet(1);
                log.info("Thread: {}", idx);

                StopWatch sw = new StopWatch();
                sw.start();

                rt.getForObject(url, String.class);

                sw.stop();
                log.info("Elapsed: {} {}", idx ,sw.getTotalTimeSeconds());
            });
        }

        es.shutdown();
        es.awaitTermination(100, TimeUnit.SECONDS);

        main.stop();
        log.info("Total: {}", main.getTotalTimeSeconds());
    }
}
