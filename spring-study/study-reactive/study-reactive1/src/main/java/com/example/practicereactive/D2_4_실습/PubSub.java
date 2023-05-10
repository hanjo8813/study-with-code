package com.example.practicereactive.D2_4_실습;

public class PubSub {

    private static RedisSavePublisher publisher;

    public static void serverInitialized() {
        // publish 는 bean 등으로 singleTon
        RedisSavePublisher pub = new RedisSavePublisher();
        publisher = pub;

        // subscriber 는 bean 등으로 singleTon.. -> application launch 시 subscriber 를 등록한다.
        // callee 와 caller 가 다를 수 있음!!
        RedisSaveDelegateSubscriber subscriber = new RedisSaveDelegateSubscriber();
        pub.subscribe(subscriber);
    }

    public static void main(String[] args){
        serverInitialized();
        // 어디선가 이벤트가 발생했어요
        executeSomething(publisher);
    }

    public static void executeSomething(RedisSavePublisher publisher){
        publisher.publish(new RedisValueDto("key", "value"), 10);
    }

}