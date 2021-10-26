package com.example.teamalfa.repository.documentSign;

import com.example.teamalfa.model.documentSign.SignedDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignedDocumentRepository extends JpaRepository<SignedDocument, Long> {
}
