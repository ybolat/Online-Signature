package com.example.teamalfa.controller.documentSign;

import com.example.teamalfa.dto.requestDto.documentSign.SignedDocumentDtoRequest;
import com.example.teamalfa.dto.responseDto.documentSign.DocumentForSigningDtoResponse;
import com.example.teamalfa.dto.responseDto.documentSign.SignedDocumentDtoResponse;
import com.example.teamalfa.model.documentSign.SignedDocument;
import com.example.teamalfa.service.documentSign.SignedDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/signed-document")
public class SingedDocumentController {

    private final SignedDocumentService signedDocumentService;

    @Autowired
    public SingedDocumentController(SignedDocumentService signedDocumentService) {
        this.signedDocumentService = signedDocumentService;
    }

    @GetMapping("/get-document-for-signing/receiver")
    public ResponseEntity<List<SignedDocumentDtoResponse>> getDocumentForSigningByReceiver(Principal principal) {
        return new ResponseEntity<>(signedDocumentService.getSignedDocumentByReceiver(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/get-document-for-signing/sender")
    public ResponseEntity<List<SignedDocumentDtoResponse>> getDocumentForSigningBySender(Principal principal) {
        return new ResponseEntity<>(signedDocumentService.getSignedDocumentBySender(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/create-signed-document")
    public ResponseEntity<HttpStatus> createSignedDocument(@RequestBody SignedDocumentDtoRequest signedDocumentDtoRequest, Principal principal) {
        signedDocumentService.createSignedDocument(signedDocumentDtoRequest, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
