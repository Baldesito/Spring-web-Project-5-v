package com.example.Spring_Web_Project_D_5_V.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_prenotazione;

    @Column(nullable = false)
    private LocalDate data_richiesta;

    private String note;
}
