package com.example.demokafka.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentDto {

    private String documentId;
    private Long docNumber;
    private Long documentType;
    private String name;
    private AuthorDto author;
    private Long store;
    private LocalDateTime created;
    private String contents;
    private String source;
}
