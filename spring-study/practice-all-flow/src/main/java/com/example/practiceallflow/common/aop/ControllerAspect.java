package com.example.practiceallflow.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("execution(* com.example.practiceallflow.controller.*.*(..))")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("□ ControllerAOP - Before : " + methodName + " 메소드가 call 되었습니다");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("□ ControllerAOP - AfterReturning : " + methodName + " 메소드가 return 되었습니다");
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("□ ControllerAOP - After : " + methodName + " 메소드가 종료 되었습니다");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("□ ControllerAOP - Around start : " + methodName + " 메소드가 시작되었습니다");
        Object result = joinPoint.proceed();
        log.info("□ ControllerAOP - Around end : " + methodName + " 메소드가 끝났습니다");
        return result;
    }

}
