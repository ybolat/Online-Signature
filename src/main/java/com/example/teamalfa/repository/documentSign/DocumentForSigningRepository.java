package com.example.teamalfa.repository.documentSign;

import com.example.teamalfa.model.documentSign.DocumentForSigning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentForSigningRepository extends JpaRepository<DocumentForSigning, Long> {
    List<DocumentForSigning> findByUserReceiverUsername(String username);
    List<DocumentForSigning> findByUserSenderUsername(String username);
}
