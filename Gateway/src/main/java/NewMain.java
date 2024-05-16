
import View.Monitor;
import gateway.Gateway;
import java.util.ArrayList;
import java.util.List;
import protocol.IProtocolReceiver;
import protocol.IProtocolSender;
import protocol.sensor.coap.ProtocolReceiverCoap;
import protocol.sensor.mqtt.ProtocolReceiverMqqt;
import protocol.server.rabbit.ProtocolSenderRabbit;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Daniel
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Gateway gateway = new Gateway("gateway1");

        //Sensores
        List<IProtocolReceiver> receivers = new ArrayList<>();

        String broker = "tcp://broker.emqx.io:1883";
        String clientId = "gateway";
        String topic = "sensor/gateway1";
        ProtocolReceiverMqqt protocolReceiverMqtt = new ProtocolReceiverMqqt(broker, clientId, topic);
        protocolReceiverMqtt.setGateway(gateway);
        receivers.add(protocolReceiverMqtt);

        String resource = "gateway1";
        ProtocolReceiverCoap protocolReceiverCoap = new ProtocolReceiverCoap(resource);
        protocolReceiverCoap.setGateway(gateway);
        receivers.add(protocolReceiverCoap);

        //Server
        IProtocolSender sender = new ProtocolSenderRabbit("server");
        gateway.setSensors(receivers);
        gateway.setServer(sender);

        gateway.startGateway();

        //pantalla
        Monitor monitor = new Monitor(gateway);
        gateway.agregarObservador(monitor);
        monitor.setVisible(true);
    }

}
