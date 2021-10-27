package com.example.teamalfa.service.documentSign;

import com.example.teamalfa.constant.documentSign.DocumentForSigningStatus;
import com.example.teamalfa.dto.requestDto.documentSign.SignedDocumentDtoRequest;
import com.example.teamalfa.dto.responseDto.documentSign.SignedDocumentDtoResponse;
import com.example.teamalfa.exception.ExceptionDescription;
import com.example.teamalfa.exception.domain.RepositoryException;
import com.example.teamalfa.mapper.docuement.SignedDocumentMapper;
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
import java.util.List;
import java.util.stream.Collectors;

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

    public List<SignedDocumentDtoResponse> getSignedDocumentBySender(String username) {
        SignedDocumentMapper signedDocumentMapper = new SignedDocumentMapper();
        return signedDocumentRepository.findByUserSenderUsername(username).stream().map(signedDocumentMapper::signedDocumentToDto)
                .collect(Collectors.toList());
    }

    public List<SignedDocumentDtoResponse> getSignedDocumentByReceiver(String username) {
        SignedDocumentMapper signedDocumentMapper = new SignedDocumentMapper();
        return signedDocumentRepository.findByUserReceiverUsername(username).stream().map(signedDocumentMapper::signedDocumentToDto)
                .collect(Collectors.toList());
    }

    public void createSignedDocument(SignedDocumentDtoRequest signedDocumentDtoRequest, Principal principal) {
        DocumentForSigning documentForSigning = documentForSigningService.findById(signedDocumentDtoRequest.getDocumentForSigningId());

        User user = userService.findByUsername(principal.getName());

        if (user.getId().equals(documentForSigning.getUserReceiver().getId()) && documentForSigning.getStatus().equals(DocumentForSigningStatus.SENT)) {

            String b64 = documentService.signDocument(documentForSigning.getDocument().getPdfBase64(), documentForSigning.getDocument().getFileName(), signedDocumentDtoRequest.getSingImage64());

            SignedDocument signedDocument = new SignedDocument();
            signedDocument.setUserSender(documentForSigning.getUserSender());
            signedDocument.setUserReceiver(documentForSigning.getUserReceiver());

            Document document = documentService.updateDocument(documentForSigning.getDocument(), b64);
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
