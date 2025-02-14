package com.example.Spring_Web_Project_D_5_V.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDipendente;

    @Column(nullable = false, unique = true)
    private String username;

    private String nome;
    private String cognome;

    @Column(unique = true)
    private String email;
}
