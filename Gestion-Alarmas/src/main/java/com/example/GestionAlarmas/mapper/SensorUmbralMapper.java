/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestionAlarmas.mapper;

import com.example.GestionAlarmas.entity.SensorUmbral;
import com.example.GestionAlarmas.entity.Umbral;
import com.mycompany.utilities.dto.SensorUmbralDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Daniel Alameda
 */

@Component
public class SensorUmbralMapper {
    
    @Autowired
    private UmbralMapper umbralMapper;
    
    public SensorUmbralDto mapperToSensorUmbralDto(SensorUmbral sensorUmbral) {
        return new SensorUmbralDto(
                sensorUmbral.getId_sensor_umbral(),
                umbralMapper.mapperToUmbralDto(sensorUmbral.getUmbral()),
                sensorUmbral.getId_sensor());
    }
    
    public SensorUmbral mapperToSensorUmbral(SensorUmbralDto sensorUmbralDto) {
        return new SensorUmbral(
                sensorUmbralDto.getId_sensor_umbral(),
                sensorUmbralDto.getId_sensor(),
                umbralMapper.mapperToUmbral(sensorUmbralDto.getUmbralesDto()));
    }
    
}
