package com.example.teamalfa.dto.responseDto.documentSign;

import com.example.teamalfa.dto.responseDto.UserDtoResponse;
import com.example.teamalfa.dto.responseDto.document.DocumentDtoResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentForSigningDtoResponse {
    private Long id;
    private DocumentDtoResponse document;
    private UserDtoResponse userSender;
    private UserDtoResponse userReceiver;
    private String status;
    private LocalDateTime createdDate;
}
