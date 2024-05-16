package com.example.GestionAlarmas.facade;

import com.mycompany.utilities.dto.AlarmaDto;
import com.mycompany.utilities.dto.SensorUmbralDto;
import com.mycompany.utilities.dto.UmbralesDto;
import com.mycompany.utilities.intercambio.ResponseFormat;

public interface Facade {

    ResponseFormat createAlarma(AlarmaDto alarmaDto);

    ResponseFormat readAlarma(Long id_alarma);

    ResponseFormat readAllAlarmas();

    ResponseFormat createSensorUmbral(SensorUmbralDto sensorUmbralDto);

    ResponseFormat readSensorUmbral(Long id_sensor_umbral);

    ResponseFormat readAllSensorUmbral();

    ResponseFormat createUmbral(UmbralesDto umbralesDto);

    ResponseFormat readUmbral(Long id_umbral);

    ResponseFormat readAllUmbrales();
}
