package com.seberino.transcoder.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
    @Bean
    public AmazonS3 amazonS3Client(AWSCredentialsProvider awsCredentialsProvider, @Value("${cloud.aws.region.static}") String region) {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(region)
                .build();
    }
}
