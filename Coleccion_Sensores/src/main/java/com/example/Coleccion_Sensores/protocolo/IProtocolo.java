/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.Coleccion_Sensores.protocolo;

import com.example.Coleccion_Sensores.protocolo.impl.ProtocoloMqtt;
import com.example.Coleccion_Sensores.protocolo.impl.ProtocoloCoap;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.example.Coleccion_Sensores.domain.Sensor;

/**
 *
 * @author Daniel
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProtocoloMqtt.class, name = "mqtt"),
    @JsonSubTypes.Type(value = ProtocoloCoap.class, name = "coap")})
public interface IProtocolo {

    public void conectar() throws Exception;

    public void desconectar() throws Exception;

    public void publicar(Sensor sensor) throws Exception;
}
