package com.example.practicereactive.D2_4_실습;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class RedisSavePublisher implements Publisher<RedisValueDto> {

    private RedisSaveSubscriptionHandler handler;

    @Override
    public void subscribe(Subscriber<? super RedisValueDto> s) {
        this.handler = new RedisSaveSubscriptionHandler(s);
        s.onSubscribe(handler);
    }

    public void publish(RedisValueDto dto, int n){
        handler.dto = dto;
        handler.request(n);
    }
}
