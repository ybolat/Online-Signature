package com.example.teamalfa.controller.documentSign;

import com.example.teamalfa.dto.requestDto.documentSign.DocumentForSigningDtoRequest;
import com.example.teamalfa.dto.responseDto.documentSign.DocumentForSigningDtoResponse;
import com.example.teamalfa.model.documentSign.DocumentForSigning;
import com.example.teamalfa.service.documentSign.DocumentForSigningService;
import com.itextpdf.text.pdf.PdfDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/document-for-signing")
public class DocumentForSigningController {

    private final DocumentForSigningService documentForSigningService;

    @Autowired
    public DocumentForSigningController(DocumentForSigningService documentForSigningService) {
        this.documentForSigningService = documentForSigningService;
    }

    @GetMapping("/get-document-for-signing/receiver")
    public ResponseEntity<List<DocumentForSigningDtoResponse>> getDocumentForSigningByReceiver(Principal principal) {
        return new ResponseEntity<>(documentForSigningService.getDocumentForSingingByReceiver(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/get-document-for-signing/sender")
    public ResponseEntity<List<DocumentForSigningDtoResponse>> getDocumentForSigningBySender(Principal principal) {
        return new ResponseEntity<>(documentForSigningService.getDocumentForSingingSender(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/create-document-for-signing")
    public ResponseEntity<HttpStatus> createDocumentForSigning(@RequestBody DocumentForSigningDtoRequest documentForSigningDtoRequest, Principal principal) {
        documentForSigningService.createDocumentForSigning(documentForSigningDtoRequest, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
