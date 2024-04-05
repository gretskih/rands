package ru.job4j.restxml.exception;

public class ControllerException extends Exception {
    public ControllerException(String message, Exception exception) {
        super(message, exception);
    }
}
