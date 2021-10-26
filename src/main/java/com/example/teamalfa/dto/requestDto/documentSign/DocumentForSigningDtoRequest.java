package com.example.teamalfa.dto.requestDto.documentSign;

import lombok.Data;

@Data
public class DocumentForSigningDtoRequest {
    private String userReceiveUsername;
    private String fileName;
    private String documentBase64;
}
