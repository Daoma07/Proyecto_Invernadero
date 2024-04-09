/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sensor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import sensor.medicion.IMedicion;

/**
 *
 * @author Daniel
 */
public class Muestra {

    private List<IMedicion> mediciones;
    private String fechaRegistro;

    public Muestra() {
    }

    public Muestra(List<IMedicion> mediciones) {
        this.mediciones = mediciones;
    }

    public void sensarMuestra() {
        for (IMedicion medicion : mediciones) {
            medicion.capturar();
        }
        formatoFecha();
    }

    private void formatoFecha() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaHoraFormateada = fechaHoraActual.format(formato);
        fechaRegistro = fechaHoraFormateada;
    }

    public List<IMedicion> getMediciones() {
        return mediciones;
    }

    public void setMediciones(List<IMedicion> mediciones) {
        this.mediciones = mediciones;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Muestra{" + "mediciones=" + mediciones + ", fechaRegistro=" + fechaRegistro + '}';
    }

}
