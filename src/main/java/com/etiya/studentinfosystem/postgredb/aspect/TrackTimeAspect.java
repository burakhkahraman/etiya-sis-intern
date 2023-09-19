package com.etiya.studentinfosystem.postgredb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class TrackTimeAspect {
    @Around("@annotation(trackTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint, TrackTime trackTime) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();  // Hedef metodu çalıştır

        long endTime = System.currentTimeMillis();
        System.out.println("Method " + joinPoint.getSignature().getName() + " executed in " + (endTime - startTime) + "ms");

        return result;
    }
}
