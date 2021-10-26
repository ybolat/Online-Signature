package com.example.teamalfa.controller.documentSign;

import com.example.teamalfa.dto.requestDto.documentSign.SignedDocumentDtoResponse;
import com.example.teamalfa.service.documentSign.SignedDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/v1/signed-document")
public class SingedDocumentController {

    private final SignedDocumentService signedDocumentService;

    @Autowired
    public SingedDocumentController(SignedDocumentService signedDocumentService) {
        this.signedDocumentService = signedDocumentService;
    }

    @PostMapping("/create-signed-document")
    public ResponseEntity<HttpStatus> createSignedDocument(@RequestBody SignedDocumentDtoResponse signedDocumentDtoResponse, Principal principal) {
        signedDocumentService.createSignedDocument(signedDocumentDtoResponse, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
