package com.example.teamalfa.service.documentSign;

import com.example.teamalfa.constant.documentSign.DocumentForSigningStatus;
import com.example.teamalfa.dto.requestDto.documentSign.DocumentForSigningDtoResponse;
import com.example.teamalfa.exception.ExceptionDescription;
import com.example.teamalfa.exception.domain.CustomNotFoundException;
import com.example.teamalfa.exception.domain.RepositoryException;
import com.example.teamalfa.model.document.Document;
import com.example.teamalfa.model.documentSign.DocumentForSigning;
import com.example.teamalfa.repository.UserRepository;
import com.example.teamalfa.repository.document.DocumentRepository;
import com.example.teamalfa.repository.documentSign.DocumentForSigningRepository;
import com.example.teamalfa.service.UserService;
import com.example.teamalfa.service.document.DocumentService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class DocumentForSigningService {

    private final DocumentForSigningRepository documentForSigningRepository;
    private final UserService userService;
    private final DocumentService documentService;


    public DocumentForSigningService(DocumentForSigningRepository documentForSigningRepository, UserService userService, DocumentService documentService) {
        this.documentForSigningRepository = documentForSigningRepository;
        this.userService = userService;
        this.documentService = documentService;
    }

    public DocumentForSigning findById(Long id) {
        return  documentForSigningRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(String.format(ExceptionDescription.CustomNotFoundException, "Document for signing", "id")));
    }

    public void createDocumentForSigning(DocumentForSigningDtoResponse documentForSigningDtoResponse, Principal principal) {
        DocumentForSigning documentForSigning = new DocumentForSigning();
        documentForSigning.setUserSender(userService.findByUsername(principal.getName()));
        documentForSigning.setUserReceiver(userService.findByUsername(documentForSigningDtoResponse.getUserReceiveUsername()));

        Document document = documentService.createDocument(documentForSigningDtoResponse.getDocumentBase64());
        documentForSigning.setDocument(document);

        documentForSigning.setStatus(DocumentForSigningStatus.SENT);

        try {
            documentForSigningRepository.save(documentForSigning);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "document for signing", "creating"));
        }
    }

    public void updateStatus(DocumentForSigning documentForSigning, String status) {
        documentForSigning.setStatus(status);
        try {
            documentForSigningRepository.save(documentForSigning);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "document for signing", "updating"));
        }
    }
}
