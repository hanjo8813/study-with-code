package com.example.practicereactive.D1_2_pubsub1;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.*;
import java.util.concurrent.TimeUnit;

/**
 * 앞서 봤듯이 Observable 문제점 해결한 유틸이 pubsub
 * 주요 내용은 아래와 같음
 *
 * publisher == observable
 * subscriber == observer
 * subscription
 * processor
 *
 * reactive stream은 이런 유틸 기반으로 인터페이스를 구축했음
 * 근데 이번 실습은 순수 자바걸로 함
 */

public class PubSub {

    public static void temp1() throws InterruptedException {
        Iterable<Integer> iter = List.of(1, 2, 3, 4, 5);
        ExecutorService es = Executors.newSingleThreadExecutor();

        Publisher p = new Publisher() {
            @Override
            public void subscribe(Subscriber subscriber) {

                Iterator<Integer> it = iter.iterator();

                subscriber.onSubscribe(
                        new Subscription() {
                            // 구독 후 요청
                            @Override
                            public void request(long n) {
                                es.execute(() -> {
                                    int i = 0;
                                    while (i++ < n) {
                                        if (it.hasNext()) {
                                            subscriber.onNext(it.next());
                                        } else {
                                            subscriber.onComplete();
                                            break;
                                        }
                                    }
                                });
                            }

                            @Override
                            public void cancel() {

                            }
                        }
                );
            }
        };

        Subscriber<Integer> s = new Subscriber<Integer>() {

            Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe");
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                System.out.println(Thread.currentThread().getName() + " onNext " + item);
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        p.subscribe(s);

        es.awaitTermination(10, TimeUnit.HOURS);
        es.shutdown();
    }


    public static void main(String[] args) throws InterruptedException {
        temp1();
    }
}