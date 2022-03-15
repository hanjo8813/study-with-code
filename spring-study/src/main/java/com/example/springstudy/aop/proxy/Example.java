package com.example.springstudy.aop.proxy;

public class Example {
    // 기능정의
    interface Dummy {
        void doSomething();
    }

    // target
    public static class DummyImpl implements Dummy{
        @Override
        public void doSomething(){
            System.out.println("DO");
        }
    }

    // proxy
    public static class DummyProxy implements Dummy{
        Dummy target;

        DummyProxy(Dummy target){
            this.target = target;
        }

        @Override
        public void doSomething(){
            System.out.println("proxy 적용 드간다");
            target.doSomething();
        }
    }

    // client
    public static void main(String[] args){
        Dummy target = new DummyImpl();
        Dummy proxy = new DummyProxy(target);
        proxy.doSomething();
    }
}
