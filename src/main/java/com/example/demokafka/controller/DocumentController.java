package com.example.demokafka.controller;

import com.example.demokafka.controller.dto.DocumentDto;
import com.example.demokafka.service.DocumentSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = DocumentController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {

    static final String REST_URL = "/requests";
    private final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentSendService documentSendService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void handleReceivedDocument(@RequestBody DocumentDto documentDto) {
        logger.info("получен документ ='{}'", documentDto);
        documentSendService.send(documentDto);
    }
}
