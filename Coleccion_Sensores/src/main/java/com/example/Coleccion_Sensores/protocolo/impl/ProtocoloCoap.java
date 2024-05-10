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
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import com.example.Coleccion_Sensores.domain.Sensor;

/**
 *
 * @author Daniel
 */
public class ProtocoloCoap implements IProtocolo {

    private transient CoapClient client;
    private String coapServerUri;

    public ProtocoloCoap() {
    }

    public ProtocoloCoap(String coapServerUri) {
        this.coapServerUri = coapServerUri;
    }

    @Override
    public void conectar() throws Exception {
    }

    @Override
    public void desconectar() throws Exception {
        if (client != null) {
            client.shutdown();
        }
    }

    @Override
    public void publicar(Sensor sensor) throws Exception {

        if (client == null) {
            client = new CoapClient(coapServerUri);
        }

        try {
            String mensaje = convertirObjetoJson(sensor);
            CoapResponse response = client.post(mensaje, 0);

            if (response != null && response.isSuccess()) {
                System.out.println("Datos enviados exitosamente al servidor CoAP");
            } else {
                throw new RuntimeException("Error al enviar datos al servidor CoAP: " + (response != null ? response.getResponseText() : "sin respuesta"));
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt(); // Restablece el estado de interrupción
            throw new Exception("Error durante la publicación de datos a CoAP", e);
        } finally {
            client.shutdown(); // Asegúrate de liberar recursos
        }
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

    public String getCoapServerUri() {
        return coapServerUri;
    }

    @Override
    public String toString() {
        return "ProtocoloCoap{" + "coapServerUri=" + coapServerUri + '}';
    }

}
