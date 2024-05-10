/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.Coleccion_Sensores.dao;

import java.util.List;
import com.example.Coleccion_Sensores.domain.Sensor;

/**
 *
 * @author Daniel
 */
public interface ISensorDAO {

    public void agregarSensor(Sensor sensor);

    public void actualizarSensor(Sensor sensor);

    public Sensor consultarSensor(String serie);

    public List<Sensor> consultarTodosSensores();
}
