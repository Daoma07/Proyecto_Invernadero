
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Daniel
 */
public class pruebas2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear servidor CoAP
        CoapServer server = new CoapServer();

        // Definir recurso CoAP para recibir datos del sensor
        CoapResource resource = new CoapResource("gateway1") {
            @Override
            public void handlePOST(CoapExchange exchange) {
                // Obtener datos del sensor del cuerpo del mensaje
                String payload = exchange.getRequestText();

                // Procesar los datos recibidos
                System.out.println("Datos del sensor recibidos: " + payload);

                // Puedes realizar acciones adicionales aquí, como almacenar los datos en una base de datos, etc.
                // Responder al sensor
                exchange.respond("Datos recibidos correctamente");
            }
        };

        // Agregar recurso al servidor
        server.add(resource);

        // Iniciar el servidor CoAP
        server.start();
        System.out.println("Servidor CoAP iniciado. Esperando datos del sensor...");
    }

}
