package com.example.GestionAlarmas.service.impl;

import com.example.GestionAlarmas.entity.SensorUmbral;
import com.example.GestionAlarmas.mapper.SensorUmbralMapper;
import com.example.GestionAlarmas.repository.SensorUmbralRepository;
import com.example.GestionAlarmas.service.SensorUmbralService;
import com.mycompany.utilities.dto.SensorUmbralDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorUmbralServiceImpl implements SensorUmbralService {

    @Autowired
    private SensorUmbralRepository sensorUmbralRepository;

    @Autowired
    private SensorUmbralMapper SensorUmbralMapper;

    @Override
    public SensorUmbralDto createSensorUmbral(SensorUmbralDto sensorUmbralDto) {
        SensorUmbral sensorUmbral = SensorUmbralMapper.mapperToSensorUmbral(sensorUmbralDto);
        sensorUmbral = sensorUmbralRepository.save(sensorUmbral);
        return SensorUmbralMapper.mapperToSensorUmbralDto(sensorUmbral);
    }

    @Override
    public SensorUmbralDto readSensorUmbral(Long id_sensor_umbral) {
        SensorUmbral sensorUmbral = sensorUmbralRepository.findById(id_sensor_umbral).orElse(null);
        return SensorUmbralMapper.mapperToSensorUmbralDto(sensorUmbral);
    }

    @Override
    public List<SensorUmbralDto> readAllSensorUmbral() {
        List<SensorUmbral> sensorUmbrals = sensorUmbralRepository.findAll();
        List<SensorUmbralDto> sensorUmbralsDto = new ArrayList<>();
        for (SensorUmbral sensorUmbral : sensorUmbrals) {
            sensorUmbralsDto.add(SensorUmbralMapper.mapperToSensorUmbralDto(sensorUmbral));
        }
        return sensorUmbralsDto;
    }

}
