package com.example.Spring_Web_Project_D_5_V.service;


import com.example.Spring_Web_Project_D_5_V.entity.Dipendente;
import com.example.Spring_Web_Project_D_5_V.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public List<Dipendente> findAll(){
        return dipendenteRepository.findAll();
    }

    public Optional<Dipendente> findById(Long id){
        return dipendenteRepository.findById(id);
    }

    public Dipendente save(Dipendente dipendente){
        if (dipendenteRepository.existsByEmail(dipendente.getEmail())){
            throw new IllegalArgumentException("L'email è gia usato");
        } return dipendenteRepository.save(dipendente);
    }

    public void deleteById(Long id) {
        dipendenteRepository.deleteById(id);
    }
}
