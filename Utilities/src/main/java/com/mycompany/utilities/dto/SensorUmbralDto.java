package com.mycompany.utilities.dto;

import java.io.Serializable;

public class SensorUmbralDto implements Serializable {

    private Long id_sensor_umbral;
    private UmbralesDto umbralesDto;
    private SensorDto sensorDto;

    public SensorUmbralDto() {
    }

    public SensorUmbralDto(UmbralesDto umbralesDto, SensorDto sensorDto) {
        this.umbralesDto = umbralesDto;
        this.sensorDto = sensorDto;
    }

    public SensorUmbralDto(Long id_sensor_umbral, UmbralesDto umbralesDto, SensorDto sensorDto) {
        this.id_sensor_umbral = id_sensor_umbral;
        this.umbralesDto = umbralesDto;
        this.sensorDto = sensorDto;
    }

    public Long getId_sensor_umbral() {
        return id_sensor_umbral;
    }

    public void setId_sensor_umbral(Long id_sensor_umbral) {
        this.id_sensor_umbral = id_sensor_umbral;
    }

    public UmbralesDto getUmbralesDto() {
        return umbralesDto;
    }

    public void setUmbralesDto(UmbralesDto umbralesDto) {
        this.umbralesDto = umbralesDto;
    }

    public SensorDto getSensorDto() {
        return sensorDto;
    }

    public void setSensorDto(SensorDto sensorDto) {
        this.sensorDto = sensorDto;
    }

    @Override
    public String toString() {
        return "SensorUmbralDto{" + "id_sensor_umbral=" + id_sensor_umbral + ", umbralesDto=" + umbralesDto + ", sensorDto=" + sensorDto + '}';
    }

}
