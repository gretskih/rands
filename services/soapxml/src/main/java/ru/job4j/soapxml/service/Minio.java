package ru.job4j.soapxml.service;

import ru.job4j.soapxml.exception.ServiceException;

public interface Minio {
    byte[] getObject(String objectName) throws ServiceException;
}
