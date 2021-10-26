package com.example.teamalfa.service.document;

import com.example.teamalfa.exception.ExceptionDescription;
import com.example.teamalfa.exception.domain.RepositoryException;
import com.example.teamalfa.model.document.Document;
import com.example.teamalfa.repository.document.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document createDocument(String documentBase64) {
        Document document = new Document();
        document.setPdfBase64(documentBase64);
        try {
            return documentRepository.save(document);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "document", "creating"));
        }
    }

    public Document updateDocument(Document document, String documentBase64) {
        document.setPdfBase64(documentBase64);
        try {
             return documentRepository.save(document);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "document", "updating"));
        }
    }
}
