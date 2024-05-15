package com.example.Administrador_Sensores.mapper;

import com.example.Administrador_Sensores.entity.Muestra;
import com.example.Administrador_Sensores.entity.Sensor;
import com.mycompany.utilities.dto.MuestraDto;
import com.mycompany.utilities.dto.SensorDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorMapper {

    @Autowired
    private MuestraMapper muestraMapper;

    public SensorDto mapperToSensorDto(Sensor sensor) {
        List<MuestraDto> muestraDtos = new ArrayList<>();
        for (Muestra muestra : sensor.getMuestras()) {
            muestraDtos.add(muestraMapper.mapperToMuestraDto(muestra));
        }

        return new SensorDto(
                sensor.getId_sensor(),
                sensor.getSerie(),
                sensor.getLocalizacion(),
                sensor.getProtocolo(),
                sensor.getGateway(),
                muestraDtos,
                sensor.getId_invernadero()
        );
    }

    public Sensor mapperToSensor(SensorDto sensorDto) {

        List<Muestra> muestras = new ArrayList<>();
        for (MuestraDto muestrasDto : sensorDto.getMuestrasDtos()) {
            muestras.add(muestraMapper.mapperToMuestra(muestrasDto));
        }

        return new Sensor(
                sensorDto.getId_sensor(),
                sensorDto.getSerie(),
                sensorDto.getLocalizacion(),
                sensorDto.getProtocolo(),
                sensorDto.getGateway(),
                sensorDto.getId_invernadero(),
                muestras);
    }
}
