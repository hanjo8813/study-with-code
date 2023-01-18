package com.example.practicereactive.D4_1_future;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * FutureTask 래핑해서 콜백패턴 구현하기
 * 콜백 함수 -> Functional 인터페이스
 */
@Slf4j
public class FutureEx2 {
    interface SuccessCallback {
        void onSuccess(String result);
    }

    interface ExceptionCallback {
        void onError(Throwable t);
    }

    public static class CallbackFutureTask extends FutureTask<String> {
        SuccessCallback sc;
        ExceptionCallback ec;

        public CallbackFutureTask(Callable<String> callable, SuccessCallback sc, ExceptionCallback ec) {
            super(callable);
            this.sc = Objects.requireNonNull(sc);
            this.ec = Objects.requireNonNull(ec);
        }

        @Override
        protected void done() {
            try {
                sc.onSuccess(get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                ec.onError(e.getCause());
            }
        }
    }

    /**
     * 아래 코드는 기술적 코드와 비즈니스 로직이 혼재되어 있음 -> 분리해보자~
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newCachedThreadPool();

        CallbackFutureTask f = new CallbackFutureTask(
                () -> {
                    Thread.sleep(2000);
                    if (1 == 1) throw new RuntimeException("Async ERROR!!!");
                    log.info("Async");
                    return "Hello";
                },
                s -> System.out.println("Result: " + s),
                e -> System.out.println("Error: " + e.getMessage())
        );

        es.execute(f);
        es.shutdown();
    }
}
