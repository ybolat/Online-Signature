package com.example.teamalfa.dto.responseDto.document;

import lombok.Data;

@Data
public class DocumentDtoResponse {
    private Long id;
    private String documentBase64;
    private String fileName;
}
