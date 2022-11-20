package com.hyberlet.quendless.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingAspect {
    @Around("allServiceMethods()")
    public Object loggingTime(ProceedingJoinPoint joinPoint) {
        long begin = System.currentTimeMillis();
        Object res = null;
        try {
            res = joinPoint.proceed();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature().getName() + ": " + (end - begin) + " ms");
        return res;
    }
    @Pointcut("within(com.hyberlet.quendless.service.*)")
    public void allServiceMethods() {}
}
