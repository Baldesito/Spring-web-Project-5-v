package com.example.Spring_Web_Project_D_5_V.payloadDTO;

import lombok.Data;

import java.time.LocalDate;


@Data
public class PrenotazioneDTO {

    private LocalDate data_richiesta;
    private String note;

}
