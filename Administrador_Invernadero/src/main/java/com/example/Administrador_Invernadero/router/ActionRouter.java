package com.example.Administrador_Invernadero.router;

import com.example.Administrador_Invernadero.entity.Invernadero;
import com.example.Administrador_Invernadero.facade.Facade;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.utilities.dto.InvernaderoDto;
import com.mycompany.utilities.dto.SensorDto;
import com.mycompany.utilities.intercambio.RequestFormat;
import com.mycompany.utilities.intercambio.ResponseFormat;
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

    private Facade facade;

    private ObjectMapper objectMapper;

    @Autowired
    public ActionRouter(Map<String, Function<String, ResponseFormat>> actionMap, Facade facade, ObjectMapper objectMapper) {
        this.actionMap = actionMap;
        this.facade = facade;
        this.objectMapper = objectMapper;
    }

    private void inicializarRouter() {
        actionMap.put("read-invernadero", this::findInvernadero);
//        actionMap.put("read-all-muestras", this::readAllMuestra);
    }

    private ResponseFormat findInvernadero(String content) {
        try {
            InvernaderoDto invernaderoDto = objectMapper.readValue(content, InvernaderoDto.class);
            return facade.readInvernadero(invernaderoDto.getId_invernadero());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ActionRouter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
