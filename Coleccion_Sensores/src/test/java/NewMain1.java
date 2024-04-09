
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Daniel
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // String broker = "tcp://mqtt.eclipse.org:1883";
        String broker = "tcp://broker.emqx.io:1883";
        String clientId = "gateway";
        String topic = "sensor/gateway1";

        MqttSubscriber mqttSubscriber = new MqttSubscriber(broker, clientId);

        try {
            mqttSubscriber.connect();

            mqttSubscriber.subscribe(topic, new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Conexión perdida con el broker");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Mensaje recibido del tema '" + topic + "': " + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("Entrega completa");
                }
            });
            // Esperar un tiempo antes de desconectar
            // mqttSubscriber.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
