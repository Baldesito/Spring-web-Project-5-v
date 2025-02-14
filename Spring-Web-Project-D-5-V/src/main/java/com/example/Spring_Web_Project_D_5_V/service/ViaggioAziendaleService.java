package com.example.Spring_Web_Project_D_5_V.service;



import com.example.Spring_Web_Project_D_5_V.entity.ViaggioAziendale;
import com.example.Spring_Web_Project_D_5_V.repository.ViaggoAziendaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ViaggioAziendaleService {

    @Autowired
    private ViaggoAziendaleRepository viaggoAziendaleRepository;

    public List<ViaggioAziendale> findAll(){
        return  viaggoAziendaleRepository.findAll();
    }

   public Optional<ViaggioAziendale> findById(@PathVariable Long id) {
        return viaggoAziendaleRepository.findById(id);
    }

    public ViaggioAziendale save(ViaggioAziendale viaggioAziendale){
        return viaggoAziendaleRepository.save(viaggioAziendale);
    }

    public void deleteById(Long id){
        viaggoAziendaleRepository.deleteById(id);
    }
}
