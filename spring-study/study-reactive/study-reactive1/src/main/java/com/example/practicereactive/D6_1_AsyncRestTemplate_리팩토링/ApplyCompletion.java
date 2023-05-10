package com.example.practicereactive.D6_1_AsyncRestTemplate_리팩토링;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.function.Function;

public class ApplyCompletion<S, T> extends Completion<S, T> {

    Function<S, ListenableFuture<T>> fn;

    public ApplyCompletion(Function<S, ListenableFuture<T>> fn) {
        this.fn = fn;
    }

    @Override
    void run(S value) {
        ListenableFuture<T> lf = fn.apply(value);
        lf.addCallback(s -> complete(s), e -> error(e));
    }
}