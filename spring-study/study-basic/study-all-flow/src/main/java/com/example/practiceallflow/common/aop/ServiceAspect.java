package com.example.practiceallflow.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ServiceAspect {

    @Pointcut("execution(* com.example.practiceallflow.service.*.*(..))")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("○ ServiceAOP - Before : " + methodName + " 메소드가 call 되었습니다");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("○ ServiceAOP - AfterReturning : " + methodName + " 메소드가 return 되었습니다");
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("○ ServiceAOP - After : " + methodName + " 메소드가 종료 되었습니다");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("○ ServiceAOP - Around start : " + methodName + " 메소드가 시작되었습니다");
        Object result = joinPoint.proceed();
        log.info("○ ServiceAOP - Around end : " + methodName + " 메소드가 끝났습니다");
        return result;
    }

}
