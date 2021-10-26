package com.example.teamalfa.dto.requestDto.documentSign;

import lombok.Data;

@Data
public class SignedDocumentDtoRequest {
    private Long documentForSigningId;
    private String documentBase64;
}
