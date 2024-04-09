/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import protocol.IProtocolReceiver;
import protocol.IProtocolSender;

/**
 *
 * @author Daniel
 */
public class Gateway implements IGateway {

    private String series;
    private List<IProtocolReceiver> sensors;
    private IProtocolSender server;

    public Gateway() {
    }

    public Gateway(String series) {
        this.series = series;
    }

    public Gateway(String series, List<IProtocolReceiver> sensors, IProtocolSender server) {
        this.series = series;
        this.sensors = sensors;
        this.server = server;
    }

    public void startGateway() {
        startSensor();
        starServer();
    }

    private void startSensor() {
        for (IProtocolReceiver protocolReceiver : sensors) {
            protocolReceiver.connect();
            protocolReceiver.subscribe();
        }
    }

    private void starServer() {
        server.connect();
    }

    public void finishGateway() {
        for (IProtocolReceiver protocolReceiver : sensors) {
            protocolReceiver.desconnect();
        }
    }

    @Override
    public void processMessage(String message) {
        message = assignGateway(message);
        server.send(message);
    }

    private String assignGateway(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(message);

            ((ObjectNode) rootNode).put("gateway", series);

            String json = mapper.writeValueAsString(rootNode);
            return json;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public void setSensors(List<IProtocolReceiver> sensors) {
        this.sensors = sensors;
    }

    public void setServer(IProtocolSender server) {
        this.server = server;
    }

}
