package com.example.Spring_Web_Project_D_5_V.service;

import com.example.Spring_Web_Project_D_5_V.entity.Dipendente;
import com.example.Spring_Web_Project_D_5_V.payloadDTO.DipendenteDTO;
import com.example.Spring_Web_Project_D_5_V.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Long insertDipendente(DipendenteDTO nuovoDipendenteDTO){
        if (dipendenteRepository.findByUsername(nuovoDipendenteDTO.getUsername()) != null) {
            throw new RuntimeException("Username già esistente.");
        }

        Dipendente nuovoDip = dto_Entity(nuovoDipendenteDTO);
        return dipendenteRepository.save(nuovoDip).getIdDipendente();
    }

    public DipendenteDTO getDipendente(Long id){
        Optional<Dipendente> dipendente = dipendenteRepository.findById(id);
        return dipendente.map(this::entity_dto).orElseThrow(() -> new RuntimeException("Il dipendente con chiave " + id + " non è presente nel sistema"));
    }

    public List<DipendenteDTO> getAllDipendenti(){
        List<Dipendente> listaDipendenti = dipendenteRepository.findAll();
        List<DipendenteDTO> listaDTO = new ArrayList<>();
        for (Dipendente dipendente : listaDipendenti){
            listaDTO.add(entity_dto(dipendente));
        }
        return listaDTO;
    }

    public void deleteById(Long id) {
        if (!dipendenteRepository.existsById(id)) {
            throw new RuntimeException("Il dipendente con chiave " + id + " non esiste.");
        }
        dipendenteRepository.deleteById(id);
    }

    public void uploadProfileImage(Long dipendenteId, MultipartFile file) {
        Optional<Dipendente> dipendenteOpt = dipendenteRepository.findById(dipendenteId);

        if (dipendenteOpt.isPresent()) {
            Dipendente dipendente = dipendenteOpt.get();
        } else {
            throw new RuntimeException("Dipendente non trovato.");
        }
    }

    private DipendenteDTO entity_dto(Dipendente dipendenteEntity){
        DipendenteDTO dto = new DipendenteDTO();
        dto.setUsername(dipendenteEntity.getUsername());
        dto.setNome(dipendenteEntity.getNome());
        dto.setCognome(dipendenteEntity.getCognome());
        dto.setEmail(dipendenteEntity.getEmail());
        dto.setPrenotazioni(dipendenteEntity.getPrenotazioni());
        return dto;
    }

    private Dipendente dto_Entity(DipendenteDTO dto) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dto.getUsername());
        dipendente.setNome(dto.getNome());
        dipendente.setCognome(dto.getCognome());
        dipendente.setEmail(dto.getEmail());
        dipendente.setPrenotazioni(dto.getPrenotazioni());
        return dipendente;
    }
}
