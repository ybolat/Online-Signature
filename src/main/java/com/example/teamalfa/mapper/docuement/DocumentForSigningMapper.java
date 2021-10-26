package com.example.teamalfa.mapper.docuement;

import com.example.teamalfa.dto.responseDto.documentSign.DocumentForSigningDtoResponse;
import com.example.teamalfa.mapper.UserMapper;
import com.example.teamalfa.model.documentSign.DocumentForSigning;

public class DocumentForSigningMapper {

    public DocumentForSigningDtoResponse documentForSigningToDto(DocumentForSigning documentForSigning) {
        DocumentForSigningDtoResponse documentForSigningDtoResponse = new DocumentForSigningDtoResponse();
        documentForSigningDtoResponse.setId(documentForSigning.getId());
        documentForSigningDtoResponse.setStatus(documentForSigning.getStatus());
        documentForSigningDtoResponse.setCreatedDate(documentForSigning.getCreatedDate());
        documentForSigningDtoResponse.setUserSender(new UserMapper().userToDTO(documentForSigning.getUserSender()));
        documentForSigningDtoResponse.setUserReceiver(new UserMapper().userToDTO(documentForSigning.getUserReceiver()));
        return documentForSigningDtoResponse;
    }
}
