package com.example.GestionAlarmas.facade.impl;

import com.example.GestionAlarmas.facade.Facade;
import com.example.GestionAlarmas.service.AlarmaService;
import com.example.GestionAlarmas.service.SensorUmbralService;
import com.example.GestionAlarmas.service.UmbralService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.utilities.dto.AlarmaDto;
import com.mycompany.utilities.dto.MuestraDto;
import com.mycompany.utilities.dto.SensorUmbralDto;
import com.mycompany.utilities.dto.UmbralesDto;
import com.mycompany.utilities.intercambio.ResponseFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FacadeImpl implements Facade {

    private ObjectMapper objectMapper;
    private AlarmaService alarmaService;
    private SensorUmbralService sensorUmbralService;
    private UmbralService umbralService;

    @Autowired
    public FacadeImpl(ObjectMapper objectMapper, AlarmaService alarmaService, SensorUmbralService sensorUmbralService, UmbralService umbralService) {
        this.objectMapper = objectMapper;
        this.alarmaService = alarmaService;
        this.sensorUmbralService = sensorUmbralService;
        this.umbralService = umbralService;
    }

    @Override
    public ResponseFormat createAlarma(AlarmaDto alarmaDto) {
        try {
            alarmaDto = alarmaService.createAlarma(alarmaDto);
            if (alarmaDto.getId_alarma() != null) {
                return new ResponseFormat(objectMapper.writeValueAsString(alarmaDto),
                        HttpStatus.OK.value());
            }
            return new ResponseFormat(objectMapper.writeValueAsString("No se puedo registrar la alarma"),
                    HttpStatus.NOT_FOUND.value());
        } catch (Exception ex) {
            Logger.getLogger(FacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseFormat("No se puedo registrar la alarma",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseFormat readAlarma(Long id_alarma) {
        try {
            AlarmaDto alarmaDto = alarmaService.readAlarma(id_alarma);
            if (alarmaDto != null) {
                return new ResponseFormat(objectMapper.writeValueAsString(alarmaDto),
                        HttpStatus.OK.value());
            }
            return new ResponseFormat(objectMapper.writeValueAsString("sin registros asociados"),
                    HttpStatus.NOT_FOUND.value());
        } catch (Exception ex) {
            Logger.getLogger(FacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseFormat("No se pudo buscar la alarma",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseFormat readAllAlarmas() {
        try {
            List<AlarmaDto> alarmasDto = alarmaService.readAllAlarmas();
            if (!alarmasDto.isEmpty() || alarmasDto != null) {
                return new ResponseFormat(objectMapper.writeValueAsString(alarmasDto),
                        HttpStatus.OK.value());
            }
            return new ResponseFormat(objectMapper.writeValueAsString("No existen registros de alarmas"),
                    HttpStatus.NOT_FOUND.value());
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseFormat("No se puedo puedo consultar las alarmas",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseFormat createSensorUmbral(SensorUmbralDto sensorUmbralDto) {
        try {
            sensorUmbralDto = sensorUmbralService.createSensorUmbral(sensorUmbralDto);
            if (sensorUmbralDto.getId_sensor_umbral() != null) {
                return new ResponseFormat(objectMapper.writeValueAsString(sensorUmbralDto),
                        HttpStatus.OK.value());
            }
            return new ResponseFormat(objectMapper.writeValueAsString("No se puedo registrar el umbral"),
                    HttpStatus.NOT_FOUND.value());
        } catch (Exception ex) {
            Logger.getLogger(FacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseFormat("No se puedo registrar el umbral",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseFormat readSensorUmbral(Long id_sensor_umbral) {
        try {
            SensorUmbralDto sensorUmbralDto = sensorUmbralService.readSensorUmbral(id_sensor_umbral);
            if (sensorUmbralDto != null) {
                return new ResponseFormat(objectMapper.writeValueAsString(sensorUmbralDto),
                        HttpStatus.OK.value());
            }
            return new ResponseFormat(objectMapper.writeValueAsString("sin registros asociados"),
                    HttpStatus.NOT_FOUND.value());
        } catch (Exception ex) {
            Logger.getLogger(FacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseFormat("No se pudo buscar el sensor_umbral",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseFormat readAllSensorUmbral() {
        try {
            List<SensorUmbralDto> sensorUmbralDto = sensorUmbralService.readAllSensorUmbral();
            if (!sensorUmbralDto.isEmpty() || sensorUmbralDto != null) {
                return new ResponseFormat(objectMapper.writeValueAsString(sensorUmbralDto),
                        HttpStatus.OK.value());
            }
            return new ResponseFormat(objectMapper.writeValueAsString("No hay registro de sensores con umbrales"),
                    HttpStatus.NOT_FOUND.value());
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseFormat("No se puedo puedo consultar los sensores con umbrale",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseFormat createUmbral(UmbralesDto umbralesDto) {
        try {
            umbralesDto = umbralService.createUmbral(umbralesDto);
            if (umbralesDto.getId_umbral() != null) {
                return new ResponseFormat(objectMapper.writeValueAsString(umbralesDto),
                        HttpStatus.OK.value());
            }
            return new ResponseFormat(objectMapper.writeValueAsString("No se puedo registrar el umbral"),
                    HttpStatus.NOT_FOUND.value());
        } catch (Exception ex) {
            Logger.getLogger(FacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseFormat("No se puedo registrar el umbral",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseFormat readUmbral(Long id_umbral) {
        try {
            UmbralesDto umbralesDto = umbralService.readUmbral(id_umbral);
            if (umbralesDto != null) {
                return new ResponseFormat(objectMapper.writeValueAsString(umbralesDto),
                        HttpStatus.OK.value());
            }
            return new ResponseFormat(objectMapper.writeValueAsString("sin registros asociados"),
                    HttpStatus.NOT_FOUND.value());
        } catch (Exception ex) {
            Logger.getLogger(FacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseFormat("No se pudo buscar el umbral",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseFormat readAllUmbrales() {
        try {
            List<UmbralesDto> umbralesDto = umbralService.readAllUmbrales();
            if (!umbralesDto.isEmpty() || umbralesDto != null) {
                return new ResponseFormat(objectMapper.writeValueAsString(umbralesDto),
                        HttpStatus.OK.value());
            }
            return new ResponseFormat(objectMapper.writeValueAsString("No existen registros de umbrales"),
                    HttpStatus.NOT_FOUND.value());
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseFormat("No se puedo puedo consultar los umbrales",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
