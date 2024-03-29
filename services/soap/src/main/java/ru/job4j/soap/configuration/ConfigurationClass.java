package ru.job4j.soap.configuration;

import io.minio.MinioClient;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @Value("${minio.url}")
    private String minioUrl;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder().credentials(accessKey, secretKey)
                .endpoint(minioUrl, 9000, false).build();
        return minioClient;
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        org.modelmapper.config.Configuration conf = modelMapper.getConfiguration();
        conf.setFieldMatchingEnabled(true);
        conf.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        conf.setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        return modelMapper;
    }

}