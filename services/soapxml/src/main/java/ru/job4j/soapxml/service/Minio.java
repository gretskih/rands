package ru.job4j.soapxml.service;

public interface Minio {
    byte[] getObject(String objectName);
}
