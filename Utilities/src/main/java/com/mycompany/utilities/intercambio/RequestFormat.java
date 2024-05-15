package com.mycompany.utilities.intercambio;

import java.io.Serializable;

public class RequestFormat implements Serializable {

    private String contentenido;
    private String metodo;

    public RequestFormat() {
    }

    public RequestFormat(String contentenido, String metodo) {
        this.contentenido = contentenido;
        this.metodo = metodo;
    }

    public String getContentenido() {
        return contentenido;
    }

    public void setContentenido(String contentenido) {
        this.contentenido = contentenido;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    @Override
    public String toString() {
        return "Request{" + "contentenido=" + contentenido + ", metodo=" + metodo + '}';
    }

}
