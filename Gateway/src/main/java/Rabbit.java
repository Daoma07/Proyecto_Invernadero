
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

    private final static String QUEUE_NAME = "server";

    public static void main(String[] argv) throws Exception {
        // Configurar la conexión y el canal
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Cambia esto según la configuración de tu servidor RabbitMQ
        factory.setUsername("root");
        factory.setPassword("1234");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();// Declarar la cola
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Esperando mensajes. Para salir, presiona Ctrl+C");

        // Definir el callback para procesar los mensajes recibidos
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("------------------------------------------");
            System.out.println("Mensaje recibido: '" + message + "'");
            System.out.println("------------------------------------------");
        };

        // Registrar el consumidor en la cola
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });

    }

}
