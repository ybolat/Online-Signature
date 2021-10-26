package com.example.teamalfa.service.documentSign;

import com.example.teamalfa.dto.requestDto.documentSign.DocumentForSigningDtoResponse;
import com.example.teamalfa.exception.ExceptionDescription;
import com.example.teamalfa.exception.domain.RepositoryException;
import com.example.teamalfa.model.document.Document;
import com.example.teamalfa.model.documentSign.DocumentForSigning;
import com.example.teamalfa.repository.UserRepository;
import com.example.teamalfa.repository.document.DocumentRepository;
import com.example.teamalfa.repository.documentSign.DocumentForSigningRepository;
import com.example.teamalfa.service.document.DocumentService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class DocumentForSigningService {

    private final DocumentForSigningRepository documentForSigningRepository;
    private final UserRepository userRepository;
    private final DocumentService documentService;


    public DocumentForSigningService(DocumentForSigningRepository documentForSigningRepository, UserRepository userRepository, DocumentService documentService) {
        this.documentForSigningRepository = documentForSigningRepository;
        this.userRepository = userRepository;
        this.documentService = documentService;
    }

    public void createDocumentForSigning(DocumentForSigningDtoResponse documentForSigningDtoResponse, Principal principal) {
        DocumentForSigning documentForSigning = new DocumentForSigning();
        userRepository.findByUsername(principal.getName()).ifPresent(documentForSigning::setUserSender);
        if (documentForSigningDtoResponse.getUserReceiveId() != null) {
            userRepository.findById(documentForSigningDtoResponse.getUserReceiveId()).ifPresent(documentForSigning::setUserReceiver);
        }
        if (Strings.isNotBlank(documentForSigningDtoResponse.getDocumentBase64())) {
            Document document = documentService.createDocument(documentForSigningDtoResponse.getDocumentBase64());
            documentForSigning.setDocument(document);
        }
        try {
            documentForSigningRepository.save(documentForSigning);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "document for signing", "creating"));
        }
    }
}
