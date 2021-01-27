package com.seberino.transcoder.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.seberino.transcoder.config.AwsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3Client;
    private final AwsProperties properties;

    public File getFileFromS3(String fileName) throws IOException {
        return this.convertToFile(this.downloadFileFromS3bucket(fileName, properties.getAwsServices().getBucketName()));
    }

    private S3Object downloadFileFromS3bucket(String fileName, String bucketName) {
        S3Object object = amazonS3Client.getObject(bucketName, fileName);
        return object;
    }

    private File convertToFile(S3Object object) throws IOException {
        InputStream in = object.getObjectContent();
        File tmp = File.createTempFile(object.getKey(), ".mp4");
        Files.copy(in, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        in.close();
        log.info("{}", tmp.getPath());

        return tmp;
    }
}
