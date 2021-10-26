package com.example.teamalfa.mapper.docuement;

import com.example.teamalfa.dto.responseDto.document.DocumentDtoResponse;
import com.example.teamalfa.model.document.Document;

public class DocumentMapper {

    public DocumentDtoResponse documentToDto(Document document) {
        DocumentDtoResponse documentDtoResponse = new DocumentDtoResponse();
        documentDtoResponse.setId(document.getId());
        documentDtoResponse.setDocumentBase64(document.getPdfBase64());
        return documentDtoResponse;
    }
}
