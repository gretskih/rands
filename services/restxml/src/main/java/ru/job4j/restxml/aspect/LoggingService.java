package ru.job4j.restxml.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.job4j.restxml.domain.Student;
import ru.job4j.restxml.model.Account;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
@Component
public class LoggingService {

    /**
     * Все классы пакет service
     */
    @Pointcut("execution(* ru.job4j.restxml.service.*.*(..))")
    public void serviceMethodExecuting() {
    }

    /**
     * класс AccountService метод save()
     */
    @Pointcut("execution(* ru.job4j.restxml.service.AccountService.save(..))")
    public void accountServiceSave() {
    }

    /**
     * класс StudentServiceImpl метод findOneStudent()
     */
    @Pointcut("execution(* ru.job4j.restxml.service.StudentServiceImpl.findOneStudent(..))")
    public void studentServiceImplFindOneStudent() {
    }

    /**
     * класс StudentServiceImpl метод findAllStudents()
     */
    @Pointcut("execution(* ru.job4j.restxml.service.StudentServiceImpl.findAllStudents(..))")
    public void studentServiceImplFindAllStudents() {
    }

    /**
     * класс TokenService метод generateAccessToken()
     */
    @Pointcut("execution(* ru.job4j.restxml.service.TokenService.generateAccessToken(..))")
    public void tokenServiceGenerateAccessToken() {
    }

    /**
     * перед запуском любого метода в пакете service
     * @param joinPoint
     */
    @Before(value = "serviceMethodExecuting()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Method execution: {} args={}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * после возникновения исключения в пакете service
     * @param e
     */
    @AfterThrowing(value = "serviceMethodExecuting()", throwing = "e")
    public void logTrow(Exception e) {
        log.error(e.getMessage(), e);
    }

    /**
     * после удачного завершения метода AccountService.save()
     * @param returningValue
     */
    @AfterReturning(value = "accountServiceSave()", returning = "returningValue")
    public void logAfter(Account returningValue) {
        log.info("Account created successfully: name={}, roles={}", returningValue.getName(), returningValue.getRoles());
    }

    /**
     * после удачного завершения метода StudentServiceImpl.findOneStudent()
     * @param returningValue
     */
    @AfterReturning(value = "studentServiceImplFindOneStudent()", returning = "returningValue")
    public void logAfter(Student returningValue) {
        log.info("The request was completed successfully: TestBookNumber={}, Faculty={}", returningValue.getTestBookNumber(), returningValue.getFaculty());
    }

    /**
     * после удачного завершения метода StudentServiceImpl.findAllStudents()
     * @param returningValue
     */
    @AfterReturning(value = "studentServiceImplFindAllStudents()", returning = "returningValue")
    public void logAfter(List<Student> returningValue) {
        log.info("The request was completed successfully: Number of entries={}", returningValue.size());
    }

    /**
     * после удачного завершения метода TokenService.generateAccessToken()
     * @param joinPoint
     */
    @AfterReturning(value = "tokenServiceGenerateAccessToken()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("The token creation was successful for: {}", Arrays.toString(joinPoint.getArgs()));
    }

}
