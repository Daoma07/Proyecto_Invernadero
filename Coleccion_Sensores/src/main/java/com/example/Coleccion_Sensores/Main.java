package com.example.Coleccion_Sensores;

import com.example.Coleccion_Sensores.dao.ISensorDAO;
import com.example.Coleccion_Sensores.dao.impl.SensorDAO;
import com.example.Coleccion_Sensores.view.PantallaSensores;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ISensorDAO sensorDAO = new SensorDAO("sensores");

        PantallaSensores pantallaSensores = new PantallaSensores(sensorDAO);

        pantallaSensores.setVisible(true);

    }

}
