package com.example.Spring_Web_Project_D_5_V.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table
public class ViaggioAziendale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idViaggio;

    @Column(nullable = false)
    private String destinazione;
    private LocalDate dataViaggio;
    private String stato;
}
