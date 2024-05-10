/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import observer.IObservable;
import observer.IObserver;
import protocol.IProtocolReceiver;
import protocol.IProtocolSender;

/**
 *
 * @author Daniel
 */
public class Gateway implements IGateway, IObservable {

    private String series;
    private List<IProtocolReceiver> sensors;
    private IProtocolSender server;
    private List<String> mensajes = new ArrayList<>();
    private List<IObserver> observadore = new ArrayList<>();
    private ScheduledExecutorService scheduler;

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
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::sendMessageServer, 0, 2, TimeUnit.MINUTES);
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
        if (scheduler != null) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(1, TimeUnit.MINUTES)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                scheduler.shutdownNow();
            }
        }
    }

    @Override
    public void processMessage(String message) {
        message = assignGateway(message);
        this.mensajes.add(message);
        actualizarTodos();
    }

    private void sendMessageServer() {
        synchronized (mensajes) {
            if (!mensajes.isEmpty()) {
                String jsonPayload = constructJsonArray(mensajes);
                server.send(jsonPayload);
                mensajes.clear();
            }
        }

    }

    private String constructJsonArray(List<String> messages) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonArray = mapper.writeValueAsString(messages);
            return jsonArray;
        } catch (JsonProcessingException e) {
            System.err.println("Error processing json: " + e.getMessage());
            return "[]";
        }
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

    @Override
    public void actualizarTodos() {
        for (IObserver observer : observadore) {
            observer.actualizar();
        }
    }

    @Override
    public void agregarObservador(IObserver observador) {
        this.observadore.add(observador);
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public String getSeries() {
        return series;
    }

}
