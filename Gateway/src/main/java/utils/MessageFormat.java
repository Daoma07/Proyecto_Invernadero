/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.List;

/**
 *
 * @author Daniel
 */
public class MessageFormat {

    private String serie;
    private String date;
    private List<Data> data;
    private int interval;

    public MessageFormat() {
    }

    public MessageFormat(String serie, String date, List<Data> data, int interval) {
        this.serie = serie;
        this.date = date;
        this.data = data;
        this.interval = interval;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MessageFormat{" + "serie=" + serie + ", date=" + date + ", data=" + data + ", interval=" + interval + '}';
    }

}
