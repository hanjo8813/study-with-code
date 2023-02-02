package com.example.practicereactive.D7_1_CompletableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class Example {

    /**
     * CompletableFuture 매우 기초
     * complete 메소드로 작업의 끝을 알려주는게 필요함
     * 혹은 completeExceptionally 예외 던지고 끝내던가..
     *
     * callback 안쓰고 get 쓰면 당연히 blocking
     */
    public static void temp1() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f = new CompletableFuture<>();
        //f.complete(2);
        f.completeExceptionally(new RuntimeException());
        System.out.println(f.get());
    }

    /**
     * 실행 완료 후 콜백 패턴 적용
     * runAsync -> thenRun -> thenRun
     * Runnable -> Runnable -> Runnable
     * 위 메소드들은 '실행'만 시킴
     */
    public static void temp2() throws InterruptedException {
        CompletableFuture
                .runAsync(() -> log.info("runAsync"))
                .thenRun(() -> log.info("thenRun"))
                .thenRun(() -> log.info("thenRun"));

        log.info("exit");

        ForkJoinPool.commonPool().shutdown();
        ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
    }

    /**
     * 실행에 대한 결과값을 넘겨주면서 콜백 체이닝하고, 마지막엔 소모
     * supplyAsync -> thenCompose -> thenApply -> exceptionally -> thenAccept
     * Supplier -> Function(FlatMap) -> Function(Map) -> Function -> Consumer
     *
     * 예외처리할때 체이닝 흐름 안끊기게 설정할수도 있음
     * (하지만 Function 타입이라서 throw만 하지 못함;; -> 리팩토링하면서 나옴)
     *
     * 이 코드는 모두 같은 스레드에서 수행됨
     */
    public static void temp3() throws InterruptedException {
        CompletableFuture
                .supplyAsync(() -> {
                    log.info("runAsync");
//                    if(1 == 1) throw new RuntimeException();
                    return 1;
                })
                .thenCompose(s -> {
                    log.info("thenApply {}",s);
                    return CompletableFuture.completedFuture(s + 1);
                })
                .thenApply(s2 -> {
                    log.info("thenApply {}",s2);
                    return s2 * 3;
                })
                .exceptionally(e -> -10)
                .thenAccept(s3 -> log.info("thenAccept {}",s3));

        log.info("exit");

        ForkJoinPool.commonPool().shutdown();
        ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
    }

    /**
     * 각 콜백을 각기 다른 스레드에서 수행시키기
     * 메소드에 Async 추가 + ExecutorService 를 넣어주면 됨
     */
    public static void temp4() throws InterruptedException {
        // 한번 쓴 스레드는 재사용 X (원래 다 사용하면 재사용함)
        ExecutorService es = Executors.newFixedThreadPool(10);

        CompletableFuture
                .supplyAsync(() -> {
                    log.info("runAsync");
                    return 1;
                }, es)
                .thenCompose(s -> {
                    log.info("thenApply {}",s);
                    return CompletableFuture.completedFuture(s + 1);
                })
                .thenApplyAsync(s2 -> {
                    log.info("thenApply {}",s2);
                    return s2 * 3;
                }, es)
                .thenAcceptAsync(s3 -> log.info("thenAccept {}",s3));

        log.info("exit");

        ForkJoinPool.commonPool().shutdown();
        ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        temp1();
//        temp2();
//        temp3();
        temp4();
    }
}
