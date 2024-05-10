/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Daniel
 */
import com.example.Coleccion_Sensores.domain.Sensor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.example.Coleccion_Sensores.dao.ISensorDAO;
import com.example.Coleccion_Sensores.dao.impl.SensorDAO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import com.example.Coleccion_Sensores.domain.medicion.unidad.UnidadHumedad;
import com.example.Coleccion_Sensores.domain.medicion.unidad.UnidadTemperatura;
import com.example.Coleccion_Sensores.domain.medicion.impl.Humedad;
import com.example.Coleccion_Sensores.domain.Muestra;
import com.example.Coleccion_Sensores.domain.medicion.impl.Temperatura;
import com.example.Coleccion_Sensores.protocolo.IProtocolo;
import com.example.Coleccion_Sensores.protocolo.impl.ProtocoloCoap;
import com.example.Coleccion_Sensores.protocolo.impl.ProtocoloMqtt;
import com.example.Coleccion_Sensores.domain.medicion.IMedicion;

public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Se define mediciones
        List<IMedicion> mediciones = new ArrayList<>();
        mediciones.add(new Temperatura(UnidadTemperatura.FAHRENHEIT));
        mediciones.add(new Humedad(UnidadHumedad.PENCENT));
        Muestra muestra = new Muestra(mediciones);

        //Datos para los protocolos
//        String clientId = "sensor_tft_167";
//        String broker = "tcp://broker.emqx.io:1883";
//        String topic = "sensor/gateway1";
//        IProtocolo protocolo = new ProtocoloMqtt(broker, clientId, topic);
        String coapServerUri = "coap://localhost:5683/gateway1";
        IProtocolo protocolo = new ProtocoloCoap(coapServerUri);
        //se crea sensor
        // Sensor sensor = new Sensor("ast_127", muestra, 10, protocolo);
        Sensor sensor = new Sensor("ght_567", muestra, 10, protocolo);
        ISensorDAO sensorDAO = new SensorDAO("sensores");
        sensorDAO.agregarSensor(sensor);
    }

}
