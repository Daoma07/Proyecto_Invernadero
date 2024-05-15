package com.example.Administrador_Sensores.router;

import com.example.Administrador_Sensores.service.impl.MuestraServiceImpl;
import com.example.Administrador_Sensores.service.impl.SensorServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.utilities.dto.MuestraDto;
import com.mycompany.utilities.dto.SensorDto;
import com.mycompany.utilities.formatoGateway.Data;
import com.mycompany.utilities.formatoGateway.MessageFormat;
import com.mycompany.utilities.intercambio.RequestFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Daniel
 */
@Component
public class ActionRouterGateway {

    private final Map<String, Consumer<String>> actionMap;

    @Autowired
    private MuestraServiceImpl muestraServiceImpl;

    @Autowired
    private SensorServiceImpl sensorServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    private ActionRouterGateway() {
        actionMap = new HashMap<>();
        actionMap.put("create-muestras", this::createMuestras);
    }

    private void createMuestras(String content) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            List<MessageFormat> messageFormats = objectMapper.readValue(content, new TypeReference<List<MessageFormat>>() {
            });
            for (MessageFormat messageFormat : messageFormats) {
                SensorDto sensorDto = sensorServiceImpl.readSensorSerie(messageFormat.getSerie());
                if (sensorDto != null) {
                    for (Data data : messageFormat.getData()) {
                        MuestraDto muestraDto = new MuestraDto(
                                data.getType(),
                                data.getMagnitude(),
                                LocalDateTime.parse(messageFormat.getDate(), formatter),
                                data.getValue(),
                                sensorDto.getId_sensor());
                        muestraServiceImpl.createMuestra(muestraDto);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ActionRouter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void route(RequestFormat requestFormat) {
        String method = requestFormat.getMetodo();
        String content = requestFormat.getContentenido();
        Consumer<String> action = actionMap.get(method);
        if (action != null) {
            action.accept(content);
        }
    }

}
