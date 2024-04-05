package ru.job4j.restxml.exception;

public class ServiceException extends Exception {
    public ServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
