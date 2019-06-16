package com.example.demokafka.service;

import com.example.demokafka.controller.dto.DocumentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class DocumentSendServiceImpl implements DocumentSendService {

    private final Logger logger = LoggerFactory.getLogger(DocumentSendServiceImpl.class);

    @Autowired
    private KafkaTemplate<String, DocumentDto> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    @Override
    public void send(DocumentDto documentDto) {
        logger.info("получена дто = '{}'", documentDto);
        ListenableFuture<SendResult<String, DocumentDto>> send = kafkaTemplate.send(topic, documentDto);
        send.addCallback(new ListenableFutureCallback<SendResult<String, DocumentDto>>() {
            @Override
            public void onFailure(Throwable ex) {
                throw new RuntimeException("Что-то пошло не так", ex);
            }

            @Override
            public void onSuccess(SendResult<String, DocumentDto> result) {
                logger.info("Всё прошло хорошо, вот запись ='{}', а вот метадата = '{}'", result.getProducerRecord(), result.getRecordMetadata());
            }
        });

    }
}
