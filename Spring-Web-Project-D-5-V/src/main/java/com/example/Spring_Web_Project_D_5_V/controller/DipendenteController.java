package com.example.Spring_Web_Project_D_5_V.controller;

import com.example.Spring_Web_Project_D_5_V.entity.Dipendente;
import com.example.Spring_Web_Project_D_5_V.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public List<Dipendente> findAll(){
        return dipendenteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> findById(Long id){
        Optional<Dipendente> dipendente = dipendenteService.findById(id);
        return dipendente.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());

    }

    @PostMapping
    public Dipendente save(@RequestBody Dipendente dipendente) {
        return dipendenteService.save(dipendente);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        dipendenteService.deleteById(id);
    }
}
