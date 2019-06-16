package com.example.demokafka.service;

import com.example.demokafka.controller.dto.DocumentDto;

public interface DocumentSendService {

    void send(DocumentDto documentDto);
}
