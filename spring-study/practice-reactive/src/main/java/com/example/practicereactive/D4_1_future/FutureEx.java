package com.example.practicereactive.D4_1_future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureEx {

    /**
     * execute : Runnable 받음 / 실행만 함
     */
    public static void temp1() {
        ExecutorService es = Executors.newCachedThreadPool();

        es.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            log.info("Async");
        });

        log.info("Exit");
    }

    /**
     * - executor
     * submit : Callable 받음 / 리턴값 있음
     * <p>
     * - Future
     * 비동기 작업에 대한 핸들러 정도로 생각하면 됨
     * isDone : non-blocking
     * get : blocking
     */
    public static void temp2() throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newCachedThreadPool();

        Future<String> f = es.submit(() -> {
            Thread.sleep(2000);
            log.info("Async");
            return "Hello";
        });

        System.out.println(f.isDone()); // non-blocking
        Thread.sleep(2100);
        log.info("Exit");
        System.out.println(f.isDone());

        System.out.println(f.get()); // blocking
    }

    /**
     * - FutureTask
     * executor submit + Future 라고 생각하면됨 -> Callable 인터페이스 받음
     * done : 리턴값 나오면 실행됨
     *
     * 이건 non-blocking 인가?
     * main 스레드가 block되지 않으니 non-blocking 맞지 않나?
     */
    public static void temp3() {
        ExecutorService es = Executors.newCachedThreadPool();

        FutureTask<String> f = new FutureTask<>(
                () -> {
                    Thread.sleep(2000);
                    log.info("Async");
                    return "Hello";
                }
        ) {
            @Override
            protected void done() {
                try {
                    System.out.println(get());  // hook
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        es.execute(f);
        es.shutdown();
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        temp1();
//        temp2();
        temp3();
    }
}
