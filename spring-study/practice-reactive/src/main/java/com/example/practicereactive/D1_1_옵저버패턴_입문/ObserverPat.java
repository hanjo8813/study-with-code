package com.example.practicereactive.D1_1_옵저버패턴_입문;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 기존 익숙한 방식과 옵저버 패턴 차이
 * Iterable <-> Observable (duality)
 *
 * 기존 옵저버 패턴의 문제점
 *
 * 1. 처리 완료 개념이 없다
 * 2. 예외 처리가 애매하다 (fallback 부재)
 *
 * 이걸 보완해서 자바 유틸이 나옴 -> pub/sub
 */

@SuppressWarnings("deprecation")
public class ObserverPat {

    /**
     * Iterable
     */
    public static void temp1() {
        Iterable<Integer> iter = () -> new Iterator<Integer>() {
            int i = 0;
            final static int MAX = 10;

            @Override
            public boolean hasNext() {
                return i < MAX;
            }

            @Override
            public Integer next() {
                return ++i;
            }
        };

        for (Integer i : iter) {
            System.out.println(i);
        }
    }


    /**
     * Observable
     */
    static class IntObservable extends Observable implements Runnable {

        @Override
        public void run() {
            for(int i=1; i<=10; i++){
                setChanged();
                notifyObservers(i);
            }
        }
    }

    public static void temp2() {
        Observer ob = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println(Thread.currentThread().getName() + " " + arg);
            }
        };
        IntObservable io = new IntObservable();

        // observable 에 observer 등록
        io.addObserver(ob);

        // 별도 스레드에서 observable 실행
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(io);
        System.out.println(Thread.currentThread().getName() + " - Exit");
        es.shutdown();
    }


    public static void main(String[] args) {
//        temp1();
        temp2();
    }
}