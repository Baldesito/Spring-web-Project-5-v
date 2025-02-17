package com.example.Spring_Web_Project_D_5_V.payloadDTO;

import com.example.Spring_Web_Project_D_5_V.entity.Prenotazione;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ViaggioAziendaleDTO {

    private String destinazione;
    private LocalDate data_viaggio;
    private String stato;
    private List<Prenotazione> prenotazioni;
}
