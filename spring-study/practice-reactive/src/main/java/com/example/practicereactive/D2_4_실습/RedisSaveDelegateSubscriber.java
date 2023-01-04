package com.example.practicereactive.D2_4_실습;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class RedisSaveDelegateSubscriber implements Subscriber<RedisValueDto> {

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("on event subscribed!!");
    }

    @Override
    public void onNext(RedisValueDto item) {
        System.out.printf("redis save called !! Key :: %s || value :: %s%n", item.key, item.value);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("exception throws..");
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Completed!!");
    }
}
