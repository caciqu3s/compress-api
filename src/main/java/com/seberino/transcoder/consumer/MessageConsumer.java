package com.seberino.transcoder.consumer;

import com.seberino.transcoder.config.AMQPConfig;
import com.seberino.transcoder.service.CompressService;
import com.seberino.transcoder.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer {
    private final S3Service s3Service;

    @RabbitListener(queues = AMQPConfig.QUEUE)
    public void consume(Message message) throws IOException {
        CompressService.exec(this.s3Service.getFileFromS3(new String(message.getBody())));
    }
}
