package com.example.GestionAlarmas.repository;

import com.example.GestionAlarmas.entity.Alarma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmaRepository extends JpaRepository<Alarma, Long> {

}
