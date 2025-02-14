package com.example.Spring_Web_Project_D_5_V.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
 @RequestMapping("/uploads")
public class UploadController {

     private static final String UPLOAD_DIR = "uploads/";

     @PostMapping("/dipendenti/{id}/profile-image")
     public ResponseEntity<String> uploadProfileImage(@PathVariable Long id, @RequestParam("file")MultipartFile file) {
         // Logica di caricamento del file
         if (file.isEmpty()){
             return ResponseEntity.badRequest().body("Il file è vuoto");
         } try {
             File uploadDir = new File(UPLOAD_DIR);
             if (!uploadDir.exists()) {
                 uploadDir.mkdir();
             } String filePath = UPLOAD_DIR + file.getOriginalFilename();
             file.transferTo(new File(filePath));
             return ResponseEntity.ok("Il file è stato aggiunto con successo: " + filePath );
         } catch (IOException e){
             return ResponseEntity.status(500).body("Non si puo caricare questo file: " + e.getMessage());
         }
     }




}
