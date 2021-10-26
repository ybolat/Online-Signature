package com.example.teamalfa.service.documentSign;

import com.example.teamalfa.constant.documentSign.DocumentForSigningStatus;
import com.example.teamalfa.dto.requestDto.documentSign.SignedDocumentDtoResponse;
import com.example.teamalfa.exception.ExceptionDescription;
import com.example.teamalfa.exception.domain.CustomNotFoundException;
import com.example.teamalfa.exception.domain.RepositoryException;
import com.example.teamalfa.model.User;
import com.example.teamalfa.model.document.Document;
import com.example.teamalfa.model.documentSign.DocumentForSigning;
import com.example.teamalfa.model.documentSign.SignedDocument;
import com.example.teamalfa.repository.documentSign.SignedDocumentRepository;
import com.example.teamalfa.service.UserService;
import com.example.teamalfa.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class SignedDocumentService {

    private final SignedDocumentRepository signedDocumentRepository;
    private final DocumentForSigningService documentForSigningService;
    private final UserService userService;
    private final DocumentService documentService;

    @Autowired
    public SignedDocumentService(SignedDocumentRepository signedDocumentRepository, DocumentForSigningService documentForSigningService, UserService userService, DocumentService documentService) {
        this.signedDocumentRepository = signedDocumentRepository;
        this.documentForSigningService = documentForSigningService;
        this.userService = userService;
        this.documentService = documentService;
    }

    public void createSignedDocument(SignedDocumentDtoResponse signedDocumentDtoResponse, Principal principal) {
        DocumentForSigning documentForSigning = documentForSigningService.findById(signedDocumentDtoResponse.getDocumentForSigningId());

        User user = userService.findByUsername(principal.getName());

        if (user.getId().equals(documentForSigning.getUserReceiver().getId())) {
            SignedDocument signedDocument = new SignedDocument();
            signedDocument.setUserSender(documentForSigning.getUserSender());
            signedDocument.setUserReceiver(documentForSigning.getUserReceiver());

            Document document = documentService.updateDocument(documentForSigning.getDocument(), signedDocumentDtoResponse.getDocumentBase64());
            signedDocument.setDocument(document);

            documentForSigningService.updateStatus(documentForSigning, DocumentForSigningStatus.SIGNED);
            try {
                signedDocumentRepository.save(signedDocument);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "signed document", "creating"));
            }
        }
    }
}
