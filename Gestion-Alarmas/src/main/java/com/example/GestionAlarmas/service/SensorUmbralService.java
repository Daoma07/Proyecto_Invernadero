package com.example.GestionAlarmas.service;

import com.mycompany.utilities.dto.SensorUmbralDto;
import java.util.List;

public interface SensorUmbralService {

    SensorUmbralDto createSensorUmbral(SensorUmbralDto sensorUmbralDto);

    SensorUmbralDto readSensorUmbral(Long id_sensor_umbral);

    List<SensorUmbralDto> readAllSensorUmbral();

}
