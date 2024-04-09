package ru.job4j.restxml.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingService {

    @Pointcut("execution(* ru.job4j.restxml.service.StudentServiceImpl.*(..))")
    public void methodExecuting() {
    }

    @Before(value = "methodExecuting()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Method execution: {} args={}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing(value = "methodExecuting()", throwing = "e")
    public void logTrow(Exception e) {
        log.error(e.getCause().getCause().toString(), e);
    }
}
