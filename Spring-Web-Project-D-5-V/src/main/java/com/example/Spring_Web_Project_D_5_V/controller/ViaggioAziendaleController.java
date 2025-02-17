package com.example.Spring_Web_Project_D_5_V.controller;

import com.example.Spring_Web_Project_D_5_V.payloadDTO.ViaggioAziendaleDTO;
import com.example.Spring_Web_Project_D_5_V.service.ViaggioAziendaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioAziendaleController {

    @Autowired
    private ViaggioAziendaleService viaggioAziendaleService;

    // POST  ----> http://localhost:8080/api/viaggi/nuovo
    @PostMapping("/nuovo")
    public ResponseEntity<String> nuovoViaggioAziendale(@RequestBody ViaggioAziendaleDTO viaggioAziendale){
        try {
            Long id_generato = viaggioAziendaleService.inserViaggio(viaggioAziendale);
            return ResponseEntity.status(HttpStatus.CREATED).body("Il viaggio con destinazione " + viaggioAziendale.getDestinazione() + " è stato salvato con successo. (KEY: " + id_generato + ")");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    // GET ----> http://localhost:8080/api/viaggi/all
    @GetMapping("/all")
    public ResponseEntity<List<ViaggioAziendaleDTO>> getAllViaggi(){
        return ResponseEntity.ok(viaggioAziendaleService.getAllViaggi());
    }

    // GET ---->  http://localhost:8080/api/viaggi/oneViaggio/{id}
    @GetMapping("/oneViaggio/{id}")
    public ResponseEntity<ViaggioAziendaleDTO> getViaggioAziendale(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(viaggioAziendaleService.getViaggioAziendale(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // DELETE -----> http://localhost:8080/api/viaggi/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try {
            viaggioAziendaleService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Viaggio eliminato con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

   // POST ----> http://localhost:8080/api/viaggi/assegnaDip/{dipendenteId}/{viaggioId}
    @PostMapping("/assegnaDip/{dipendenteId}/{viaggioId}")
    public ResponseEntity<String> assegnaDipendente(@PathVariable Long dipendenteId, @PathVariable Long viaggioId) {
        try {
            viaggioAziendaleService.assegnaDipendente(dipendenteId, viaggioId);
            return ResponseEntity.ok("Dipendente assegnato al viaggio con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT ----->  http://localhost:8080/api/viaggi/cambiaStato/{viaggioId}?nuovoStato=completato
    @PutMapping("/cambiaStato/{viaggioId}")
    public ResponseEntity<String> cambiaStatoViaggio(@PathVariable Long viaggioId, @RequestParam String nuovoStato) {
        try {
            viaggioAziendaleService.cambiaStatoViaggio(viaggioId, nuovoStato);
            return ResponseEntity.ok("Stato del viaggio cambiato a " + nuovoStato + ".");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
