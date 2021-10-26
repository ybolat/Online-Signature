package com.example.teamalfa.service.document;

import com.example.teamalfa.exception.ExceptionDescription;
import com.example.teamalfa.exception.domain.RepositoryException;
import com.example.teamalfa.model.document.Document;
import com.example.teamalfa.repository.document.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document createDocument(String username, String documentBase64, String fileName) {
        Document document = new Document();
        document.setPdfBase64(documentBase64);
        document.setFileName(fileName);
        saveInFolder(username, fileName, documentBase64);
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "document", "updating"));
        }
    }

    String saveInFolder(String receiver, String fileName, String documentBase64) {
        File file = new File("C:" + File.separator + "Documents" + File.separator + receiver + File.separator + fileName);
        if (file.mkdir()) {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                byte[] decoder = Base64.getDecoder().decode(documentBase64);
                fos.write(decoder);
                return file.getPath();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "document", "creating"));
            }
        }
        throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "document", "creating"));
    }

}
