package ru.job4j.soapxml.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingService {

    @Pointcut("execution(* ru.job4j.soapxml.service.StudentService.*(..))")
    public void methodExecuting() {
    }

    @Before(value = "methodExecuting()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Method execution: {} args={}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing(value = "methodExecuting()", throwing = "e")
    public void logTrow(Exception e) {
        log.error(e.getCause().toString(), e);
    }
}
