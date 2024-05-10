package com.example.Coleccion_Sensores.domain;

/**
 *
 * @author Daniel
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.Coleccion_Sensores.protocolo.IProtocolo;

public class Sensor {

    private String serie;
    private Muestra muestra;
    private int intervaloTiempo;
    private IProtocolo protocolo;
    private transient ScheduledExecutorService scheduler;
    private boolean estatus;

    public Sensor() {
    }

    public Sensor(String serie, Muestra muestra, int intervaloTiempo, IProtocolo protocolo) {
        this.serie = serie;
        this.muestra = muestra;
        this.intervaloTiempo = intervaloTiempo;
        this.protocolo = protocolo;
    }

    public void iniciarSensor() {

        if (estatus && scheduler != null && !scheduler.isShutdown()) {
            System.out.println("Sensor ya está en ejecución");
            return;
        }
        setEstatus(true);
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newScheduledThreadPool(1);
        }
        try {
            protocolo.conectar();
            Runnable metodo = () -> {
                tomarMuestra();
                try {
                    protocolo.publicar(this);
                } catch (Exception ex) {
                    Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(this.toString());
            };
            scheduler.scheduleAtFixedRate(metodo, 0, intervaloTiempo, TimeUnit.SECONDS);
        } catch (Exception ex) {
            setEstatus(false);
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pararSensor() throws Exception {
        if (!estatus) {
            System.out.println("Sensor no está activo");
            return;
        }
        setEstatus(false);
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
        try {
            protocolo.desconectar();
        } catch (Exception ex) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tomarMuestra() {
        muestra.sensarMuestra();
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSerie() {
        return serie;
    }

    public Muestra getMuestra() {
        return muestra;
    }

    public int getIntervaloTiempo() {
        return intervaloTiempo;
    }

    public IProtocolo getProtocolo() {
        return protocolo;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "Sensor{serie=" + serie + ", muestra=" + muestra + ", intervaloTiempo=" + intervaloTiempo + '}';
    }

}
