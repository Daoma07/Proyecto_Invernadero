package com.example.GestionAlarmas.repository;

import com.example.GestionAlarmas.entity.SensorUmbral;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorUmbralRepository extends JpaRepository<SensorUmbral, Long> {

    // List<SensorUmbral> findByIdSensor(Long id_sensor);
}
