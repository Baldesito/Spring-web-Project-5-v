package com.example.Spring_Web_Project_D_5_V.service;

import com.example.Spring_Web_Project_D_5_V.entity.ViaggioAziendale;
import com.example.Spring_Web_Project_D_5_V.payloadDTO.ViaggioAziendaleDTO;
import com.example.Spring_Web_Project_D_5_V.repository.DipendenteRepository;

import com.example.Spring_Web_Project_D_5_V.entity.Dipendente;
import com.example.Spring_Web_Project_D_5_V.entity.Prenotazione;
import com.example.Spring_Web_Project_D_5_V.repository.ViaggoAziendaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViaggioAziendaleService {

    @Autowired
    private ViaggoAziendaleRepository viaggioAziendaleRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Long inserViaggio(ViaggioAziendaleDTO nuovoViaggioAziendaleDTO) {
        ViaggioAziendale nuovoViaggioAziendale = dtoToEntity(nuovoViaggioAziendaleDTO);
        return viaggioAziendaleRepository.save(nuovoViaggioAziendale).getIdViaggio();
    }

    public ViaggioAziendaleDTO getViaggioAziendale(Long id) {
        Optional<ViaggioAziendale> viaggioAziendale = viaggioAziendaleRepository.findById(id);
        return viaggioAziendale.map(this::entityToDto).orElseThrow(() -> new RuntimeException("Viaggio non disponibile"));
    }

    public List<ViaggioAziendaleDTO> getAllViaggi() {
        List<ViaggioAziendale> lista = viaggioAziendaleRepository.findAll();
        List<ViaggioAziendaleDTO> listaDTO = new ArrayList<>();
        for (ViaggioAziendale viaggioAziendale : lista) {
            listaDTO.add(entityToDto(viaggioAziendale));
        }
        return listaDTO;
    }

    public void deleteById(Long id) {
        viaggioAziendaleRepository.deleteById(id);
    }

    public void assegnaDipendente(Long dipendenteId, Long viaggioId) {
        Optional<Dipendente> dipendenteOpt = dipendenteRepository.findById(dipendenteId);
        Optional<ViaggioAziendale> viaggioOpt = viaggioAziendaleRepository.findById(viaggioId);

        if (dipendenteOpt.isPresent() && viaggioOpt.isPresent()) {
            Dipendente dipendente = dipendenteOpt.get();
            ViaggioAziendale viaggio = viaggioOpt.get();

            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setData_richiesta(LocalDate.now());
            prenotazione.setNote("Prenotazione automatica");

            viaggio.getPrenotazioni().add(prenotazione);
            dipendente.getPrenotazioni().add(prenotazione);

            viaggioAziendaleRepository.save(viaggio);
            dipendenteRepository.save(dipendente);
        } else {
            throw new RuntimeException("Dipendente o Viaggio non trovati.");
        }
    }

    public void cambiaStatoViaggio(Long viaggioId, String nuovoStato) {
        Optional<ViaggioAziendale> viaggioOpt = viaggioAziendaleRepository.findById(viaggioId);

        if (viaggioOpt.isPresent()) {
            ViaggioAziendale viaggio = viaggioOpt.get();
            viaggio.setStato(nuovoStato);
            viaggioAziendaleRepository.save(viaggio);
        } else {
            throw new RuntimeException("Viaggio non trovato.");
        }
    }

    private ViaggioAziendaleDTO entityToDto(ViaggioAziendale viaggioAziendale) {
        ViaggioAziendaleDTO dto = new ViaggioAziendaleDTO();
        dto.setData_viaggio(viaggioAziendale.getDataViaggio());
        dto.setDestinazione(viaggioAziendale.getDestinazione());
        dto.setPrenotazioni(viaggioAziendale.getPrenotazioni());
        dto.setStato(viaggioAziendale.getStato());
        return dto;
    }

    private ViaggioAziendale dtoToEntity(ViaggioAziendaleDTO dto) {
        ViaggioAziendale viaggioAziendale = new ViaggioAziendale();
        viaggioAziendale.setDataViaggio(dto.getData_viaggio());
        viaggioAziendale.setDestinazione(dto.getDestinazione());
        viaggioAziendale.setPrenotazioni(dto.getPrenotazioni());
        viaggioAziendale.setStato(dto.getStato());
        return viaggioAziendale;
    }
}
