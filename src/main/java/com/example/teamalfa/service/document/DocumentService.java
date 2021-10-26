package com.example.teamalfa.service.document;

import com.example.teamalfa.exception.ExceptionDescription;
import com.example.teamalfa.exception.domain.RepositoryException;
import com.example.teamalfa.model.document.Document;
import com.example.teamalfa.repository.document.DocumentRepository;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document createDocument(String documentBase64, String fileName) {
        Document document = new Document();
        document.setPdfBase64(documentBase64);
        document.setFileName(fileName);
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

    public String signDocument(String documentBase64, String fileName, String singImageBase64) {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        com.itextpdf.text.Document document1 = new com.itextpdf.text.Document();
        String documentFolder = "C:" + File.separator + "Documents" + File.separator + fileName + ".pdf";
        String singImageFolder = "C:" + File.separator + "Documents" + File.separator + fileName + "signImage" + ".pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(documentFolder));
            PdfWriter.getInstance(document1, new FileOutputStream(singImageFolder));

            document.open();
            document1.open();
            byte[] decoded = Base64.decodeBase64(documentBase64.getBytes());
            byte[] decoded1 = Base64.decodeBase64(singImageBase64.getBytes());
            document.add(Image.getInstance(decoded));
            document1.add(Image.getInstance(decoded1));
            document.close();
            document1.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "signed document", "creating"));
        }
        return "";
    }

}
