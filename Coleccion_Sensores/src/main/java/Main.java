
import dao.ISensorDAO;
import dao.SensorDAO;
import java.util.List;
import sensor.Sensor;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ISensorDAO sensorDAO = new SensorDAO("sensores");

        List<Sensor> sensores = sensorDAO.consultarTodosSensores();
        for (Sensor sensor : sensores) {
            sensor.iniciarSensor();
        }
    }

}
