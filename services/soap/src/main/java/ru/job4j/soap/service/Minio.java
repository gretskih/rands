package ru.job4j.soap.service;

public interface Minio {
    byte[] getObject(String objectName);
}
