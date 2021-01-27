package com.seberino.transcoder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties("app")
@Configuration
public class AwsProperties {
    private AwsServices awsServices;

    @Data
    public static class AwsServices{

        private String bucketName;
    }
}
