/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package protocol.server.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import protocol.IProtocolSender;

/**
 *
 * @author Daniel
 */
public class ProtocolSenderRabbit implements IProtocolSender {

    private static final String HOST = "localhost";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private final String queue;
    private ConnectionFactory factory;

    public ProtocolSenderRabbit(String queue) {
        this.queue = queue;
    }

    @Override
    public void connect() {
        factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
    }

    @Override
    public void send(String message) {

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

            String responseQueueName = queue;

            channel.basicPublish("", responseQueueName, null, message.getBytes("UTF-8"));
            System.out.println("Enviado respuesta al servidor");

        } catch (Exception e) {
            System.err.println("Error al enviar mensaje: " + e.getMessage());
        }
    }
}
