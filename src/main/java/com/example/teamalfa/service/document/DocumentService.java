package com.example.teamalfa.service.document;

import com.example.teamalfa.exception.ExceptionDescription;
import com.example.teamalfa.exception.domain.RepositoryException;
import com.example.teamalfa.model.document.Document;
import com.example.teamalfa.repository.document.DocumentRepository;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.Path;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

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
        String documentFolder = "C:" + File.separator + "Documents" + File.separator + fileName + ".pdf";
        String singImageFolder = "C:" + File.separator + "Documents" + File.separator + fileName + "SignImage" + ".png";
        Base64 decoder = new Base64();
        byte[] imgBytes = decoder.decode(singImageBase64);
        BufferedImage bufImg;
        try {
            bufImg = ImageIO.read(new ByteArrayInputStream(imgBytes));
            File imgOutFile = new File(singImageFolder);
            ImageIO.write(bufImg, "png", imgOutFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PdfWriter.getInstance(document, new FileOutputStream(documentFolder));

            document.open();
            byte[] decoded = Base64.decodeBase64(documentBase64.getBytes());
            document.add(Image.getInstance(decoded));
            document.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException(String.format(ExceptionDescription.RepositoryException, "signed document", "creating"));
        }

        com.itextpdf.text.Document document1 = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(document1, new FileOutputStream(documentFolder));
            document1.open();
            Image img = Image.getInstance(singImageFolder);
            img.setAbsolutePosition(10f, 10f);
            document1.add(img);
            document1.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        File file = new File(documentFolder);
        byte [] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(bytes);
    }

}
