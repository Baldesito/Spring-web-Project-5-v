package com.example.Spring_Web_Project_D_5_V.controller;

import com.example.Spring_Web_Project_D_5_V.payloadDTO.DipendenteDTO;
import com.example.Spring_Web_Project_D_5_V.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/dipendente")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    // POST -----> http://localhost:8080/api/dipendente/nuovo
    @PostMapping("/nuovo")
    public ResponseEntity<String> nuovoDipendente(@RequestBody DipendenteDTO nuovoDipendente){
        try {
            dipendenteService.insertDipendente(nuovoDipendente);
            return ResponseEntity.status(HttpStatus.CREATED).body("L'inserimento del dipendente " + nuovoDipendente.getCognome() + " è andato a buon fine.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // GET ---->  http://localhost:8080/api/dipendente/all
    @GetMapping("/all")
    public ResponseEntity<List<DipendenteDTO>> getAllDipendenti(){
        return ResponseEntity.ok(dipendenteService.getAllDipendenti());
    }

    // GET -----> http://localhost:8080/api/dipendentE/onedip/{id}
    @GetMapping("/onedip/{id}")
    public ResponseEntity<DipendenteDTO> getDipendente(@PathVariable("id") Long id_dipendente){
        try {
            return ResponseEntity.ok(dipendenteService.getDipendente(id_dipendente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    // DELETE -----> http://localhost:8080/api/dipendente/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try {
            dipendenteService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Dipendente eliminato con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // POST -----> http://localhost:8080/api/dipendente/uploadProfileImage/{dipendenteId}
    @PostMapping("/uploadProfileImage/{dipendenteId}")
    public ResponseEntity<String> uploadProfileImage(@PathVariable Long dipendenteId, @RequestParam("file") MultipartFile file) {
        try {
            dipendenteService.uploadProfileImage(dipendenteId, file);
            return ResponseEntity.ok("Immagine profilo caricata con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
