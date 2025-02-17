package com.example.Spring_Web_Project_D_5_V.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "viaggi_aziendali")
public class ViaggioAziendale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idViaggio;

    @Column(nullable = false)
    private String destinazione;

    @Column(nullable = false)
    private LocalDate dataViaggio;

    private String stato;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_viaggio")
    private List<Prenotazione> prenotazioni;
}
