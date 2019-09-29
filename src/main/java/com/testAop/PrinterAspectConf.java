package com.testAop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PrinterAspectConf {

    @Pointcut("execution(* com.testAop..*(..))")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object testAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("打印前......");
        Object obj = joinPoint.proceed();
        System.out.println("打印后......");
        return obj;
    }
}