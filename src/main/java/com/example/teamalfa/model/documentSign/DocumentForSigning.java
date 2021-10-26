package com.example.teamalfa.model.documentSign;

import com.example.teamalfa.model.User;
import com.example.teamalfa.model.document.Document;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "document_for_signing")
@Data
public class DocumentForSigning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_sender_id")
    private User userSender;

    @ManyToOne
    @JoinColumn(name = "user_receiver_id")
    private User userReceiver;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @Column(name = "status")
    private String status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
