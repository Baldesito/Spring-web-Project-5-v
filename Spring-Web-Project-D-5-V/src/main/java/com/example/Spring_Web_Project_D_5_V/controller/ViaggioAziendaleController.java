package com.example.Spring_Web_Project_D_5_V.controller;


import com.example.Spring_Web_Project_D_5_V.entity.ViaggioAziendale;
import com.example.Spring_Web_Project_D_5_V.service.ViaggioAziendaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/viaggi")
public class ViaggioAziendaleController {

    @Autowired
    private ViaggioAziendaleService viaggioAziendaleService;

    @GetMapping
    public List<ViaggioAziendale> findAll(){
        return viaggioAziendaleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViaggioAziendale> findById(@PathVariable Long id){
        Optional<ViaggioAziendale> viaggioAziendale = viaggioAziendaleService.findById(id);
        return viaggioAziendale.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ViaggioAziendale save(@RequestBody ViaggioAziendale viaggioAziendale){
        return viaggioAziendaleService.save(viaggioAziendale);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        viaggioAziendaleService.deleteById(id);
    }

}
