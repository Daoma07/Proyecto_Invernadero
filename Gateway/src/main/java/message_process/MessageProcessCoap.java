/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package message_process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.utilities.formatoGateway.Data;
import com.mycompany.utilities.formatoGateway.MessageFormat;

/**
 *
 * @author Daniel
 */
public class MessageProcessCoap implements IMessageProcess {

    private MessageFormat messageFormat;

    public MessageProcessCoap() {
    }

    @Override
    public MessageFormat messageFormat(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(message);

            String serie = rootNode.get("serie").asText();
            String date = rootNode.get("muestra").get("fechaRegistro").asText();
            int interval = rootNode.get("intervaloTiempo").asInt();

            List<Data> data = new ArrayList<>();
            JsonNode medicionesNode = rootNode.get("muestra").get("mediciones");
            for (JsonNode medidaNode : medicionesNode) {
                String type = medidaNode.get("type").asText();
                String magnitude = null;
                if ("temperatura".equals(type)) {
                    magnitude = medidaNode.get("unidadTemperatura").asText();
                } else if ("humedad".equals(type)) {
                    magnitude = medidaNode.get("unidadHumedad").asText();
                }
                float value = medidaNode.get("valor").floatValue();
                Data dataItem = new Data(type, magnitude, value);
                data.add(dataItem);
            }
            messageFormat = new MessageFormat(serie, date, data, interval);

            return messageFormat;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
