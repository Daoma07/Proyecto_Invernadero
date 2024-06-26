/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.Coleccion_Sensores.domain.medicion;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.example.Coleccion_Sensores.domain.medicion.impl.Humedad;
import com.example.Coleccion_Sensores.domain.medicion.impl.Temperatura;

/**
 *
 * @author Daniel
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Temperatura.class, name = "temperatura"),
    @JsonSubTypes.Type(value = Humedad.class, name = "humedad")
})
public interface IMedicion {

    public void capturar();
}
