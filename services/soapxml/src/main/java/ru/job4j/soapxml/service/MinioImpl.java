package ru.job4j.soapxml.service;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.job4j.soapxml.exception.ServiceException;

import java.io.InputStream;

@Service
public class MinioImpl implements Minio {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Autowired
    public MinioImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public byte[] getObject(String objectName) throws ServiceException {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build())) {
            return IOUtils.toByteArray(stream);
        } catch (Exception e) {
            throw new ServiceException("Error MinIo", e);
        }
    }
}
