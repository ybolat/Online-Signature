package com.example.teamalfa.mapper.docuement;

import com.example.teamalfa.dto.responseDto.documentSign.DocumentForSigningDtoResponse;
import com.example.teamalfa.dto.responseDto.documentSign.SignedDocumentDtoResponse;
import com.example.teamalfa.mapper.UserMapper;
import com.example.teamalfa.model.documentSign.SignedDocument;

public class SignedDocumentMapper {

    public SignedDocumentDtoResponse signedDocumentToDto(SignedDocument signedDocument) {
        SignedDocumentDtoResponse signedDocumentDtoResponse = new SignedDocumentDtoResponse();
        signedDocumentDtoResponse.setId(signedDocument.getId());
        signedDocumentDtoResponse.setCreatedDate(signedDocument.getCreatedDate());
        signedDocumentDtoResponse.setUserSender(new UserMapper().userToDTO(signedDocument.getUserSender()));
        signedDocumentDtoResponse.setUserReceiver(new UserMapper().userToDTO(signedDocument.getUserReceiver()));
        return signedDocumentDtoResponse;
    }
}
