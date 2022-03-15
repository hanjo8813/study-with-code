package com.example.springstudy.aop.practice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAspect {

    @Pointcut("@annotation(com.example.springstudy.aop.practice.aop.DoSomething)")
    private void pointCut(){
    }

    @Around("@annotation(com.example.springstudy.aop.practice.aop.DoSomething)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Annotation - Around : " + methodName + " 메소드가 시작되었습니다");
        Object result = joinPoint.proceed();
        System.out.println("Annotation - Around : " + methodName + " 메소드가 끝났습니다");
        return result;
    }
}
