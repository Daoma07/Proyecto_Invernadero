package com.example.Administrador_Sensores.facade;

import com.mycompany.utilities.dto.MuestraDto;
import com.mycompany.utilities.dto.SensorDto;
import com.mycompany.utilities.intercambio.ResponseFormat;

public interface IFacade {

    MuestraDto createMuestra(MuestraDto muestraDto);

    ResponseFormat readAllMuestra();

    ResponseFormat createSensor(SensorDto sensorDto);

    SensorDto readSensorSerie(String serie);

    ResponseFormat readAllSensores();
}
