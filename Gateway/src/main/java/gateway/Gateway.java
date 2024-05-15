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
import com.mycompany.utilities.formatoGateway.MessageFormat;
import com.mycompany.utilities.intercambio.RequestFormat;

/**
 *
 * @author Daniel
 */
public class Gateway implements IGateway, IObservable {

    private String series;
    private List<IProtocolReceiver> sensors;
    private IProtocolSender server;
    private List<MessageFormat> mensajes = new ArrayList<>();
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
    public void processMessage(MessageFormat message) {
        message.setGateway(series);
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

    private String constructJsonArray(List<MessageFormat> messages) {
        ObjectMapper mapper = new ObjectMapper();
        try {

            String jsonArray = mapper.writeValueAsString(messages);
            RequestFormat requestFormat = new RequestFormat(jsonArray, "create-muestras");
            String json = mapper.writeValueAsString(requestFormat);
            return json;
        } catch (JsonProcessingException e) {
            System.err.println("Error processing json: " + e.getMessage());
            return "[]";
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

    public List<MessageFormat> getMensajes() {
        return mensajes;
    }

    public String getSeries() {
        return series;
    }

}
