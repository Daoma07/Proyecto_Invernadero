/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Coleccion_Sensores.protocolo.impl;

import com.example.Coleccion_Sensores.protocolo.IProtocolo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.example.Coleccion_Sensores.domain.Sensor;

/**
 *
 * @author Daniel
 */
public class ProtocoloMqtt implements IProtocolo {

    private transient MqttClient client;
    private String broker;
    private String clientId;
    private String topic;

    public ProtocoloMqtt() {
    }

    public ProtocoloMqtt(String broker, String clientId, String topic) {
        this.broker = broker;
        this.clientId = clientId;
        this.topic = topic;
        try {
            client = new MqttClient(broker, clientId, new MemoryPersistence());
        } catch (MqttException ex) {
            Logger.getLogger(ProtocoloMqtt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void asegurarClienteMqtt() {
        if (client == null) {
            try {
                client = new MqttClient(broker, clientId, new MemoryPersistence());
            } catch (MqttException ex) {
                Logger.getLogger(ProtocoloMqtt.class.getName()).log(Level.SEVERE, "Error al inicializar el cliente MQTT", ex);
            }
        }
    }

    @Override
    public void conectar() throws MqttException {
        asegurarClienteMqtt();
        if (!client.isConnected()) {
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            client.connect(connOpts);
            System.out.println("Conectado al broker: " + broker);
        } else {
            System.out.println("Cliente ya conectado");
        }
    }

    @Override
    public void desconectar() throws MqttException {
        if (client != null && client.isConnected()) {
            client.disconnect();
            System.out.println("Desconectado del broker");
        }
    }

    @Override
    public void publicar(Sensor sensor) throws MqttException {
        String content = convertirObjetoJson(sensor);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(2);
        client.publish(topic, message);
        System.out.println("Mensaje publicado en el tema '" + topic + "': " + content);
    }

    private String convertirObjetoJson(Sensor sensor) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(sensor);
            return json;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProtocoloMqtt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "ProtocoloMqtt{" + "broker=" + broker + ", clientId=" + clientId + ", topic=" + topic + '}';
    }

}
