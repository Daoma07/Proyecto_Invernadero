/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Coleccion_Sensores.domain.medicion.impl;

import com.example.Coleccion_Sensores.domain.medicion.IMedicion;
import com.example.Coleccion_Sensores.domain.medicion.unidad.UnidadHumedad;

/**
 *
 * @author Daniel
 */
public class Humedad implements IMedicion {

    private UnidadHumedad unidadHumedad;
    private float valor;

    public Humedad() {
    }

    public Humedad(UnidadHumedad unidadHumedad) {
        this.unidadHumedad = unidadHumedad;
    }

    public void capturar() {
        int min = 30;
        int max = 90;
        this.valor = (float) (min + Math.random() * (max - min));
    }

    public UnidadHumedad getUnidadHumedad() {
        return unidadHumedad;
    }

    public void setUnidadHumedad(UnidadHumedad unidadHumedad) {
        this.unidadHumedad = unidadHumedad;
    }

    public float getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Humedad{" + "unidadHumedad=" + unidadHumedad + ", valor=" + valor + '}';
    }

}
