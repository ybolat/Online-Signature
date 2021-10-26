package com.example.teamalfa.repository.documentSign;

import com.example.teamalfa.model.documentSign.DocumentForSigning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentForSigningRepository extends JpaRepository<DocumentForSigning, Long> {
}
