package com.mycompany.utilities.dto;

import java.io.Serializable;
import java.util.List;

public class SensorDto implements Serializable {

    private Long id_sensor;
    private String serie;
    private String localizacion;
    private String protocolo;
    private String gateway;
    private List<MuestraDto> muestrasDtos;
    private Long id_invernadero;
    private List<AlarmaDto> alarmaDtos;
    private List<Long> ids_sensor_umbral;

    public SensorDto() {
    }

    public SensorDto(String serie, String localizacion, String protocolo, String gateway, Long id_invernadero) {
        this.serie = serie;
        this.localizacion = localizacion;
        this.protocolo = protocolo;
        this.gateway = gateway;
        this.id_invernadero = id_invernadero;
    }

    public SensorDto(String serie, String localizacion, String protocolo, String gateway, List<MuestraDto> muestrasDtos, Long id_invernadero, List<AlarmaDto> alarmaDtos, List<Long> ids_sensor_umbral) {
        this.serie = serie;
        this.localizacion = localizacion;
        this.protocolo = protocolo;
        this.gateway = gateway;
        this.muestrasDtos = muestrasDtos;
        this.id_invernadero = id_invernadero;
        this.alarmaDtos = alarmaDtos;
        this.ids_sensor_umbral = ids_sensor_umbral;
    }

    public SensorDto(Long id_sensor, String serie, String localizacion, String protocolo, String gateway, List<MuestraDto> muestrasDtos, Long id_invernadero, List<AlarmaDto> alarmaDtos, List<Long> ids_sensor_umbral) {
        this.id_sensor = id_sensor;
        this.serie = serie;
        this.localizacion = localizacion;
        this.protocolo = protocolo;
        this.gateway = gateway;
        this.muestrasDtos = muestrasDtos;
        this.id_invernadero = id_invernadero;
        this.alarmaDtos = alarmaDtos;
        this.ids_sensor_umbral = ids_sensor_umbral;
    }

    public Long getId_sensor() {
        return id_sensor;
    }

    public void setId_sensor(Long id_sensor) {
        this.id_sensor = id_sensor;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public List<MuestraDto> getMuestrasDtos() {
        return muestrasDtos;
    }

    public void setMuestrasDtos(List<MuestraDto> muestrasDtos) {
        this.muestrasDtos = muestrasDtos;
    }

    public Long getId_invernadero() {
        return id_invernadero;
    }

    public void setId_invernadero(Long id_invernadero) {
        this.id_invernadero = id_invernadero;
    }

    public List<AlarmaDto> getAlarmaDtos() {
        return alarmaDtos;
    }

    public void setAlarmaDtos(List<AlarmaDto> alarmaDtos) {
        this.alarmaDtos = alarmaDtos;
    }

    public List<Long> getIds_sensor_umbral() {
        return ids_sensor_umbral;
    }

    public void setIds_sensor_umbral(List<Long> ids_sensor_umbral) {
        this.ids_sensor_umbral = ids_sensor_umbral;
    }

    @Override
    public String toString() {
        return "SensorDto{" + "id_sensor=" + id_sensor + ", serie=" + serie + ", localizacion=" + localizacion + ", protocolo=" + protocolo + ", gateway=" + gateway + ", muestrasDtos=" + muestrasDtos + ", id_invernadero=" + id_invernadero + ", alarmaDtos=" + alarmaDtos + ", ids_sensor_umbral=" + ids_sensor_umbral + '}';
    }

}
