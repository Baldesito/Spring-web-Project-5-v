package com.example.Spring_Web_Project_D_5_V.repository;


import com.example.Spring_Web_Project_D_5_V.entity.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Dipendente findByUsername(String username);
}
