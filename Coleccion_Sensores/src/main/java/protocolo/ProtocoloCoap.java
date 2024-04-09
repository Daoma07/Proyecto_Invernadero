/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package protocolo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import sensor.Sensor;

/**
 *
 * @author Daniel
 */
public class ProtocoloCoap implements IProtocolo {

    private String coapServerUri;

    public ProtocoloCoap() {
    }

    public ProtocoloCoap(String coapServerUri) {
        this.coapServerUri = coapServerUri;
    }

    @Override
    public void conectar() throws Exception {
    }

    @Override
    public void desconectar() throws Exception {
    }

    @Override
    public void publicar(Sensor sensor) throws Exception {

        CoapClient client = new CoapClient(coapServerUri);

        String mensaje = convertirObjetoJson(sensor);
        CoapResponse response = client.post(mensaje, 0);

        if (response != null && response.isSuccess()) {
            System.out.println("Datos enviados exitosamente al servidor CoAP");
        } else {
            System.err.println("Error al enviar datos al servidor CoAP");
        }
    }

    private String convertirObjetoJson(Sensor sensor) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(sensor);
            return json;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProtocoloMqtt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getCoapServerUri() {
        return coapServerUri;
    }

    @Override
    public String toString() {
        return "ProtocoloCoap{" + "coapServerUri=" + coapServerUri + '}';
    }

}
