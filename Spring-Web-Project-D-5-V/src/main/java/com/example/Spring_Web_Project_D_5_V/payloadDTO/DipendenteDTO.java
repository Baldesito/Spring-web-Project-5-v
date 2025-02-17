package com.example.Spring_Web_Project_D_5_V.payloadDTO;


import com.example.Spring_Web_Project_D_5_V.entity.Prenotazione;
import lombok.Data;

import java.util.List;

@Data
public class DipendenteDTO {

    private String username;
    private String nome;
    private String cognome;
    private String email;
    private List<Prenotazione> prenotazioni;
}
