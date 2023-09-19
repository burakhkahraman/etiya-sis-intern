package com.etiya.studentinfosystem.postgredb.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // Servislerin her metodunun başında çalışacak
    @Before("execution(* com.etiya.studentinfosystem.postgredb.service..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering method: " + joinPoint.getSignature().getName() + " of class: " + joinPoint.getTarget().getClass().getSimpleName());
    }

    // Servislerin her metodunun sonunda çalışacak
    @AfterReturning(pointcut = "execution(* com.etiya.studentinfosystem.postgredb.service..*.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("Exiting method: " + joinPoint.getSignature().getName() + " of class: " + joinPoint.getTarget().getClass().getSimpleName() + " with return value: " + result);
    }
}
