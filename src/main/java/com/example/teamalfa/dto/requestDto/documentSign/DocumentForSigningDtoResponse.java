package com.example.teamalfa.dto.requestDto.documentSign;

import lombok.Data;

@Data
public class DocumentForSigningDtoResponse {
    private Long userSenderId;
    private Long userReceiveId;
    private String documentBase64;
}
