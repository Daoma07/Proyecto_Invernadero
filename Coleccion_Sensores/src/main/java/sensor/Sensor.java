package sensor;

/**
 *
 * @author Daniel
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocolo.IProtocolo;

public class Sensor {

    private String serie;
    private Muestra muestra;
    private int intervaloTiempo;
    private IProtocolo protocolo;
    private transient final ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool(1);

    public Sensor() {
    }

    public Sensor(String serie, Muestra muestra, int intervaloTiempo, IProtocolo protocolo) {
        this.serie = serie;
        this.muestra = muestra;
        this.intervaloTiempo = intervaloTiempo;
        this.protocolo = protocolo;
    }

    public void iniciarSensor() {
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
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pararSensor() {
        scheduler.shutdown();
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

    @Override
    public String toString() {
        return "Sensor{serie=" + serie + ", muestra=" + muestra + ", intervaloTiempo=" + intervaloTiempo + '}';
    }

}
