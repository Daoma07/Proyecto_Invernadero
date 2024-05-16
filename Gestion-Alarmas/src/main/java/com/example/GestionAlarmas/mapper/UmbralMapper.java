package com.example.GestionAlarmas.mapper;

import com.example.GestionAlarmas.entity.SensorUmbral;
import com.example.GestionAlarmas.entity.Umbral;
import com.example.GestionAlarmas.repository.SensorUmbralRepository;
import com.example.GestionAlarmas.repository.UmbralRepository;
import com.mycompany.utilities.dto.CondicionEnum;
import com.mycompany.utilities.dto.UmbralesDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UmbralMapper {

    @Autowired
    private SensorUmbralRepository sensorUmbralRepository;

    public UmbralesDto mapperToUmbralDto(Umbral umbral) {

        List<Long> ids_sensor_umbral = umbral.getSensorUmbrales().stream()
                .map(SensorUmbral::getId_sensor_umbral)
                .collect(Collectors.toList());

        return new UmbralesDto(
                umbral.getId_umbral(),
                umbral.getCondicion(),
                umbral.getMax(),
                umbral.getMin(),
                ids_sensor_umbral);
    }

    public Umbral mapperToUmbral(UmbralesDto umbralesDto) {
        return new Umbral(
                umbralesDto.getId_umbral(),
                umbralesDto.getCondicionEnum(),
                umbralesDto.getMax(),
                umbralesDto.getMin(),
                sensorUmbralRepository.findAllById(umbralesDto.getIds_sensor_umbral()));
    }
}
