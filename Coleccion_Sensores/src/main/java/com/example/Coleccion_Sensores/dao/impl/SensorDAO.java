/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Coleccion_Sensores.dao.impl;

import com.example.Coleccion_Sensores.dao.ISensorDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.Coleccion_Sensores.domain.Sensor;

/**
 *
 * @author Daniel
 */
public class SensorDAO implements ISensorDAO {

    private final String folderPath;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String tipoDocumento = ".json";

    public SensorDAO(String folderPath) {
        this.folderPath = folderPath;
    }

    @Override
    public void agregarSensor(Sensor sensor) {
        String nombreDocumento = nombreDocumento(sensor);
        if (validarExistenciaSensor(nombreDocumento)) {
            System.err.println("Error: Ya existe un sensor con el nombre de archivo especificado");
            return;
        }
        try {
            mapper.writeValue(new File(nombreDocumento), sensor);
            System.out.println("Sensor guardado correctamente " + nombreDocumento);
        } catch (IOException ex) {
            System.err.println("Error al guardar sensor: " + ex.getMessage());
            Logger.getLogger(SensorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizarSensor(Sensor sensor) {
        String nombreDocumento = nombreDocumento(sensor);
        if (!validarExistenciaSensor(nombreDocumento)) {
            System.err.println("Error: No existe un sensor con el nombre de archivo especificado");
            return;
        }
        try {
            mapper.writeValue(new File(nombreDocumento), sensor);
            System.out.println("Sensor actualizado correctamente " + nombreDocumento);
        } catch (IOException ex) {
            System.err.println("Error al actualizar sensor: " + ex.getMessage());
            Logger.getLogger(SensorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Sensor consultarSensor(String serie) {
        Sensor sensor = new Sensor();
        sensor.setSerie(serie);
        String nombreDocumento = nombreDocumento(sensor);
        if (validarExistenciaSensor(nombreDocumento)) {
            try {
                sensor = mapper.readValue(new File(nombreDocumento), Sensor.class);
                System.out.println("Sensor cargado correctamente desde " + nombreDocumento);
                return sensor;
            } catch (IOException ex) {
                System.err.println("Error al consultar sensor: " + ex.getMessage());
                Logger.getLogger(SensorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.err.println("Error: No existe un sensor con el nombre de archivo especificado");
        return null;
    }

    @Override
    public List<Sensor> consultarTodosSensores() {
        List<Sensor> sensores = new ArrayList<>();
        File folder = new File(folderPath);
        File[] archivos = folder.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().endsWith(tipoDocumento)) {
                    try {
                        Sensor sensor = mapper.readValue(archivo, Sensor.class);
                        sensores.add(sensor);
                    } catch (IOException ex) {
                        System.err.println("Error al leer el archivo " + archivo.getName() + ": " + ex.getMessage());
                        Logger.getLogger(SensorDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        return sensores;
    }

    //metodos de apoyo
    private String nombreDocumento(Sensor sensor) {
        return folderPath + File.separator + "sensor_" + sensor.getSerie() + tipoDocumento;
    }

    private boolean validarExistenciaSensor(String path) {
        File archivoNuevo = new File(path);
        if (archivoNuevo.exists()) {
            return true;
        }
        return false;
    }

}
