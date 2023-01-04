package com.example.practicereactive.D2_4_실습;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@RequiredArgsConstructor
public class RedisSaveSubscriptionHandler implements Subscription {

    private final Subscriber<? super RedisValueDto> _subscriber;

    public RedisValueDto dto;

    @Override
    public void request(long n) {
        _subscriber.onNext(dto);
        _subscriber.onComplete();
    }

    @Override
    public void cancel() {

    }

    // .....
}