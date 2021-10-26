package com.example.teamalfa.repository.documentSign;

import com.example.teamalfa.model.documentSign.SignedDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignedDocumentRepository extends JpaRepository<SignedDocument, Long> {
    List<SignedDocument> findByUserReceiverUsername(String username);
    List<SignedDocument> findByUserSenderUsername(String username);
}
