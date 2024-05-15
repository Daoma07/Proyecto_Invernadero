package com.example.Administrador_Sensores.router;

import com.example.Administrador_Sensores.service.impl.MuestraServiceImpl;
import com.example.Administrador_Sensores.service.impl.SensorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.utilities.dto.MuestraDto;
import com.mycompany.utilities.dto.SensorDto;
import com.mycompany.utilities.intercambio.RequestFormat;
import com.mycompany.utilities.intercambio.ResponseFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ActionRouter {

    private final Map<String, Function<String, ResponseFormat>> actionMap;

    @Autowired
    private MuestraServiceImpl muestraServiceImpl;

    @Autowired
    private SensorServiceImpl sensorServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    private ActionRouter() {
        actionMap = new HashMap<>();
        actionMap.put("create-sensor", this::createSensor);
        actionMap.put("read-all-muestras", this::readAllMuestra);
    }

    private ResponseFormat readAllMuestra(String content) {
        try {
            List<MuestraDto> muestrasDtos = muestraServiceImpl.readAllMuestra();
            if (!muestrasDtos.isEmpty() || muestrasDtos != null) {
                return new ResponseFormat(objectMapper.writeValueAsString(muestrasDtos),
                        HttpStatus.OK.value());
            }
            return new ResponseFormat(objectMapper.writeValueAsString("No se puedo consultar las muestras"),
                    HttpStatus.NOT_FOUND.value());
        } catch (Exception ex) {
            Logger.getLogger(ActionRouter.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseFormat("No se puedo puedo consultar las muestras",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private ResponseFormat createSensor(String content) {
        try {
            SensorDto sensorDto = objectMapper.readValue(content, SensorDto.class);
            sensorDto = sensorServiceImpl.createSensor(sensorDto);
            if (sensorDto.getId_sensor() != null) {
                return new ResponseFormat(objectMapper.writeValueAsString(sensorDto),
                        HttpStatus.OK.value());
            }
            return new ResponseFormat(objectMapper.writeValueAsString("No se puedo registrar el sensor"),
                    HttpStatus.NOT_FOUND.value());
        } catch (Exception ex) {
            Logger.getLogger(ActionRouter.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseFormat("No se puedo registrar el sensor",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public ResponseFormat route(RequestFormat requestFormat) {
        String method = requestFormat.getMetodo();
        String content = requestFormat.getContentenido();
        return actionMap.getOrDefault(method, this::defaultAction).apply(content);
    }

    private ResponseFormat defaultAction(String payload) {
        return new ResponseFormat("Acción no reconocida",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
