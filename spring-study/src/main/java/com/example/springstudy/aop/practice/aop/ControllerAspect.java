package com.example.springstudy.aop.practice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

    // "execution([접근지정자] [리턴타입] [패키지].[클래스].[메소드]([인자값타입]))"

    @Pointcut("execution(public String com.example.springstudy.aop.practice.controller.*.*(..))")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Before : " + methodName + " 메소드가 call 되었습니다");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("AfterReturning : " + methodName + " 메소드가 return 되었습니다");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("AfterThrowing : " + methodName + " 메소드에서 예외가 발생했습니다\n");
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature().getName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("After : " + methodName + " 메소드가 종료 되었습니다\n");
    }

    /**
     * Around는 After, AfterReturning, AfterThrowing 범위를 모두 커버한다
     * ProceedingJoinPoint 자체가 컨트롤러 메소드의 요청을 빼앗은 객체임
     */
    @Around("execution(public String com.example.springstudy.aop.practice.controller.*.test3(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        // After
        System.out.println("Around : " + methodName + " 메소드가 시작되었습니다");
        // controller 메소드를 호출함
        Object result = joinPoint.proceed();
        // AfterReturning
        System.out.println("Around : " + methodName + " 메소드가 끝났습니다\n");
        return result;
    }

}
