package com.example.teamalfa.controller.documentSign;

import com.example.teamalfa.dto.requestDto.documentSign.DocumentForSigningDtoResponse;
import com.example.teamalfa.service.documentSign.DocumentForSigningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/v1/document-for-signing")
public class DocumentForSigningController {

    private final DocumentForSigningService documentForSigningService;

    @Autowired
    public DocumentForSigningController(DocumentForSigningService documentForSigningService) {
        this.documentForSigningService = documentForSigningService;
    }

    @PostMapping("/create-document-for-signing")
    public ResponseEntity<HttpStatus> createDocumentForSigning(@RequestBody DocumentForSigningDtoResponse documentForSigningDtoResponse, Principal principal) {
        documentForSigningService.createDocumentForSigning(documentForSigningDtoResponse, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
