package com.example.GestionAlarmas.router;

import com.example.GestionAlarmas.entity.SensorUmbral;
import com.example.GestionAlarmas.entity.Umbral;
import com.example.GestionAlarmas.facade.Facade;
import com.example.GestionAlarmas.rabbit_client.RabbitMQMessageSend;
import com.example.GestionAlarmas.service.SensorUmbralService;
import com.example.GestionAlarmas.service.impl.AlarmaServiceImpl;
import com.example.GestionAlarmas.service.impl.SensorUmbralServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.utilities.dto.MuestraDto;
import com.mycompany.utilities.dto.SensorDto;
import com.mycompany.utilities.formatoGateway.Data;
import com.mycompany.utilities.formatoGateway.MessageFormat;
import com.mycompany.utilities.intercambio.RequestFormat;
import com.mycompany.utilities.intercambio.ResponseFormat;
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

@Component
public class ActionRouterGateway {

    private final Map<String, Consumer<String>> actionMap;
    private RabbitMQMessageSend rabbitMQMessageSend;
    private Facade facade;
    private ObjectMapper objectMapper;
    private SensorUmbralServiceImpl sensorUmbralServiceImpl;
    private AlarmaServiceImpl alarmaServiceImpl;

    @Autowired
    public ActionRouterGateway(Facade facade, ObjectMapper objectMapper,
            RabbitMQMessageSend rabbitMQMessageSend,
            SensorUmbralServiceImpl sensorUmbralServiceImpl,
            AlarmaServiceImpl alarmaServiceImpl) {
        this.rabbitMQMessageSend = rabbitMQMessageSend;
        actionMap = new HashMap<>();
        this.facade = facade;
        this.objectMapper = objectMapper;
        actionMap.put("create-muestras", this::validarAlarma);
        this.sensorUmbralServiceImpl = sensorUmbralServiceImpl;
        this.alarmaServiceImpl = alarmaServiceImpl;
    }

    private void validarAlarma(String content) {
        System.out.println("procesando datos");
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//            List<MessageFormat> messageFormats = objectMapper.readValue(content, new TypeReference<List<MessageFormat>>() {
//            });
//            for (MessageFormat messageFormat : messageFormats) {
//                SensorDto sensorDto = new SensorDto();
//                sensorDto.setSerie(messageFormat.getSerie());
//                String json = objectMapper.writeValueAsString(sensorDto);
//                RequestFormat requestFormat = new RequestFormat(json, "read-sensor-serie");
//                ResponseFormat respuesta = rabbitMQMessageSend.sendMessageSensor(requestFormat);
//                messageFormat.toString();
//                if (respuesta.getResponseStatus() == 200) {
//                    SensorDto sensorDtoEncontrado = objectMapper.readValue(respuesta.getContent(), SensorDto.class);
//                    SensorUmbral sensorUmbral = sensorUmbralServiceImpl.buscarPorIdSensor(sensorDtoEncontrado.getId_sensor()).get(0);
//                    if (sensorDtoEncontrado != null) {
//                        for (Data data : messageFormat.getData()) {
//                            if (data.getValue() < sensorUmbral.getUmbral().getMin()
//                                    || data.getValue() < sensorUmbral.getUmbral().getMax()) {
//                                System.out.println("Alarma activada");
//                            }
//                        }
//                    }
//
//                }
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(ActionRouterGateway.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
