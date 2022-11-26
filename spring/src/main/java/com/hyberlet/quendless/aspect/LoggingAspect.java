package com.hyberlet.quendless.aspect;

import com.hyberlet.quendless.model.Action;
import com.hyberlet.quendless.service.ActionService;
import com.hyberlet.quendless.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;

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
        Action action = new Action();
        action.setActionTime(LocalDateTime.now());
        action.setUser(userService.getCurrentUser());
        action.setContext(joinPoint.getSignature().getName());
        action.setDescription(Arrays.toString(joinPoint.getArgs()));
        actionService.createAction(action);
        return res;
    }
    //@Pointcut("within(com.hyberlet.quendless.service.*)")
    @Pointcut("@annotation(com.hyberlet.quendless.aspect.LoggedAction)")
    public void allServiceMethods() {}
}
