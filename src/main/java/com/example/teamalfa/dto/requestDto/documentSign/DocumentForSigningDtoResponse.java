package com.example.teamalfa.dto.requestDto.documentSign;

import lombok.Data;

@Data
public class DocumentForSigningDtoResponse {
    private String userReceiveUsername;
    private String documentBase64;
}