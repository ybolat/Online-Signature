package com.example.teamalfa.model.security;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorityName;
}
