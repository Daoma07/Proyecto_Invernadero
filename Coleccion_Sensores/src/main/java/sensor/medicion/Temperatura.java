/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sensor.medicion;

import sensor.medicion.unidad.UnidadTemperatura;

/**
 *
 * @author Daniel
 */
public class Temperatura implements IMedicion {

    private UnidadTemperatura unidadTemperatura;
    private float valor;

    public Temperatura() {
    }

    public Temperatura(UnidadTemperatura unidadTemperatura) {
        this.unidadTemperatura = unidadTemperatura;
    }

    public void capturar() {
        int min = 10;
        int max = 30;
        this.valor = (float) (min + Math.random() * (max - min));
    }

    public UnidadTemperatura getUnidadTemperatura() {
        return unidadTemperatura;
    }

    public float getValor() {
        return valor;
    }

    public void setUnidadTemperatura(UnidadTemperatura unidadTemperatura) {
        this.unidadTemperatura = unidadTemperatura;
    }

    @Override
    public String toString() {
        return "Temperatura{" + "unidadTemperatura=" + unidadTemperatura + ", valor=" + valor + '}';
    }

}
