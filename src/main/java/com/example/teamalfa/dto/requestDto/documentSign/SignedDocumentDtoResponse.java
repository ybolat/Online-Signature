package com.example.teamalfa.dto.requestDto.documentSign;

import lombok.Data;

@Data
public class SignedDocumentDtoResponse {
    private Long documentForSigningId;
    private String documentBase64;
}
