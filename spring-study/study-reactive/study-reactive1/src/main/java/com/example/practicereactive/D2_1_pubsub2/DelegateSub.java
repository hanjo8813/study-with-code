package com.example.practicereactive.D2_1_pubsub2;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 어댑터 클래스
 */

public class DelegateSub<T, R> implements Subscriber<T> {

    Subscriber sub;

    public DelegateSub(Subscriber<? super R> sub) {
        this.sub = sub;
    }

    @Override
    public void onSubscribe(Subscription s) {
        sub.onSubscribe(s);
    }

    @Override
    public void onNext(T o) {
        sub.onNext(o);
    }

    @Override
    public void onError(Throwable t) {
        sub.onError(t);
    }

    @Override
    public void onComplete() {
        sub.onComplete();
    }
}
