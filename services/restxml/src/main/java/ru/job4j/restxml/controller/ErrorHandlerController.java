package ru.job4j.restxml.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.job4j.restxml.dto.ErrorMessage;
import ru.job4j.restxml.exception.ControllerException;

@RestControllerAdvice(
        assignableTypes = {StudentController.class,
                            AccountController.class}
)
public class ErrorHandlerController {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ErrorMessage> onHandleException(ControllerException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorMessage(e.getCause().toString(), e.getCause().getCause().getMessage()));
    }
}
