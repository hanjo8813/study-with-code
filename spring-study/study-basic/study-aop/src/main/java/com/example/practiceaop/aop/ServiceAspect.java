package com.example.practiceaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    @Pointcut("execution(* com.example.practiceaop..*ServiceImpl.test*(..))")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Service - Around : " + methodName + " 메소드가 시작되었습니다");
        Object result = joinPoint.proceed();
        System.out.println("Service - Around : " + methodName + " 메소드가 끝났습니다");
        return result;
    }
}
