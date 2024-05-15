package com.mycompany.utilities.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MuestraDto implements Serializable {

    private Long id_muestra;
    private String serie;
    private LocalDateTime fechaHora;
    private Float valor;
    private Long id_sensor;

    public MuestraDto() {
    }

    public MuestraDto(String serie, LocalDateTime fechaHora, Float valor, Long id_sensor) {
        this.serie = serie;
        this.fechaHora = fechaHora;
        this.valor = valor;
        this.id_sensor = id_sensor;
    }

    public MuestraDto(Long id_muestra, String serie, LocalDateTime fechaHora, Float valor, Long id_sensor) {
        this.id_muestra = id_muestra;
        this.serie = serie;
        this.fechaHora = fechaHora;
        this.valor = valor;
        this.id_sensor = id_sensor;
    }

    public Long getId_muestra() {
        return id_muestra;
    }

    public void setId_muestra(Long id_muestra) {
        this.id_muestra = id_muestra;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Long getId_sensor() {
        return id_sensor;
    }

    public void setId_sensor(Long id_sensor) {
        this.id_sensor = id_sensor;
    }

    @Override
    public String toString() {
        return "MuestraDto{" + "id_muestra=" + id_muestra + ", serie=" + serie + ", fechaHora=" + fechaHora + ", valor=" + valor + ", id_sensor=" + id_sensor + '}';
    }

}
