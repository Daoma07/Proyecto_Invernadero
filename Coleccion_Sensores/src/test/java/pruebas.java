
import java.io.File;
import java.io.IOException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.elements.exception.ConnectorException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Daniel
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ConnectorException, IOException {

        String coapServerUri = "coap://localhost:5683/gateway";

        CoapClient client = new CoapClient(coapServerUri);

        double temperatura = 25.5;

        String payload = Double.toString(temperatura);
        CoapResponse response = client.post(payload, 0);

        if (response != null && response.isSuccess()) {
            System.out.println("Datos enviados exitosamente al servidor CoAP");
        } else {
            System.err.println("Error al enviar datos al servidor CoAP");
        }
    }

}
