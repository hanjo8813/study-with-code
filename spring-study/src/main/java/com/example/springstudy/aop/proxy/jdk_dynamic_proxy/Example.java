package com.example.springstudy.aop.proxy.jdk_dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Example {

    // 기능정의
    interface Dummy {
        void doSomething();
    }

    // target
    public static class DummyService implements Dummy {
        @Override
        public void doSomething() {
            System.out.println("DO");
        }
    }

    // jdk dynamic proxy
    public static class DummyProxy implements InvocationHandler {
        Dummy target;

        public DummyProxy(Dummy target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("doSomething".equals(method.getName())) {
                System.out.println("proxy 적용 드간다");
                return method.invoke(target, args);
            }
            return method.invoke(target, args);
        }
    }

    // client
    public static void main(String[] args) {
        Dummy target = new DummyService();
        Dummy proxy = (Dummy) Proxy.newProxyInstance(
            Example.class.getClassLoader(),
            new Class[]{Dummy.class},   // 인터페이스 타입을 지정해줘야함
            new DummyProxy(target)
        );
        proxy.doSomething();
    }
}
