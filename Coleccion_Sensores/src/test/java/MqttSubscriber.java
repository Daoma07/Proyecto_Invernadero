
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSubscriber {

    private MqttClient client;
    private String broker;
    private String clientId;

    public MqttSubscriber(String broker, String clientId) {
        this.broker = broker;
        this.clientId = clientId;
    }

    public void connect() throws MqttException {
        client = new MqttClient(broker, clientId, new MemoryPersistence());
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        client.connect(connOpts);
        System.out.println("Conectado al broker: " + broker);
    }

    public void disconnect() throws MqttException {
        if (client != null && client.isConnected()) {
            client.disconnect();
            System.out.println("Desconectado del broker");
        }
    }

    public void subscribe(String topic, MqttCallback callback) throws MqttException {
        client.setCallback(callback);
        client.subscribe(topic);
        System.out.println("Suscripción realizada al tema: " + topic);
    }
}
