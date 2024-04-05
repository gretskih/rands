package ru.job4j.soapxml.exception;

public class ServiceException extends Exception {
    public ServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
