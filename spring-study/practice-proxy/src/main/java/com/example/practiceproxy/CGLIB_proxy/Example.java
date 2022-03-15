package com.example.practiceproxy.CGLIB_proxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Example {

    // target
    public static class DummyService {

        public void doSomething() {
            System.out.println("DO");
        }
    }

    // proxy
    public static class DummyProxy implements MethodInterceptor {

        DummyService target;

        DummyProxy(DummyService target) {
            this.target = target;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("proxy 적용 드간다");
            return proxy.invokeSuper(obj, args);    // 상속을 통해 적용
        }
    }

    // client
    public static void main(String[] args) {
        DummyService target = new DummyService();
        DummyService proxy = (DummyService) Enhancer.create(
            DummyService.class,
            new DummyProxy(target)
        );
        proxy.doSomething();
    }

}
