
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Daniel
 */
public class Rabbit {

    private final static String EXCHANGE_NAME = "server";
    private final static String BINDING_KEY = "gateway"; // Escucha todos los mensajes

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("root");
        factory.setPassword("1234");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Declara el intercambio de tipo "topic"
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        // Declara una cola temporal y la enlaza al intercambio
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, BINDING_KEY);

        System.out.println("Esperando mensajes. Para salir, presiona Ctrl+C");

        // Definir el callback para procesar los mensajes recibidos
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("------------------------------------------");
            System.out.println("Mensaje recibido: '" + message + "'");
            System.out.println("------------------------------------------");
        };

        // Registrar el consumidor en la cola
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }

}
