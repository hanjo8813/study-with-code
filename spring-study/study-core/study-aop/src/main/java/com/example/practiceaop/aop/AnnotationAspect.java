package com.example.practiceaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAspect {

    @Pointcut("@annotation(com.example.practiceaop.aop.DoSomething)")
    private void pointCut(){
    }

    @Pointcut("@annotation(com.example.practiceaop.aop.DoSomething2)")
    private void pointCut2(){
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("Annotation - Around : " + methodName + " 메소드가 시작되었습니다");
        Object result = joinPoint.proceed();
        System.out.println("Annotation - Around : " + methodName + " 메소드가 끝났습니다");
        return result;
    }


    /**
     * PCD
     * execution : 표현식
     * within : 패키지/클래스 제한
     * args : 메소드 파라미터 뽑기
     * this : cglib 프록시
     * target : jdk dynamic 프록시
     * @target : 어노테이션 붙은 클래스 조건..
     * @args : 클래스 파라미터 뽑기
     * @within : 특정 어노테이션 붙은 클래스 찾고.. 모든 메소드에 advice 부여
     * @annotation : 어노테이션 지정
     */
    @Around("pointCut2() && args(str, num) && @annotation(annotation)")
    public Object around2(ProceedingJoinPoint joinPoint, String str, int num, DoSomething2 annotation) throws Throwable {

        System.out.println(str);
        System.out.println(num);

        System.out.println(annotation.str());
        System.out.println(annotation.num());

        Signature signature = joinPoint.getSignature();

        System.out.println(signature.getName());
//        System.out.println(signature.getClass().getName());
//        System.out.println(signature.getClass().getSimpleName());
        System.out.println(signature.getDeclaringType());

        // target의 파라미터 값
        Object[] args = joinPoint.getArgs();
        for(var arg : args){
            System.out.println(arg.toString());
        }

        Object target = joinPoint.getTarget();
        System.out.println(target.toString());

        Object result = joinPoint.proceed();

        return result;
    }
}
