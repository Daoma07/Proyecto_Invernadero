package com.example.Administrador_Invernadero.rabbit_client;

import com.mycompany.utilities.intercambio.RequestFormat;
import com.mycompany.utilities.intercambio.ResponseFormat;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageSend {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.sensor.routing.key}")
    private String sensorRoutingKey;

    @Value("${rabbitmq.alarma.routing.key}")
    private String alarmaRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public ResponseFormat sendMessageSensor(RequestFormat request) {
        try {
            return (ResponseFormat) rabbitTemplate.convertSendAndReceive(
                    exchange,
                    sensorRoutingKey,
                    request);

        } catch (Exception e) {
            return null;
        }
    }

    public ResponseFormat sendMessageAlarma(RequestFormat request) {
        try {
            return (ResponseFormat) rabbitTemplate.convertSendAndReceive(
                    exchange,
                    alarmaRoutingKey,
                    request);

        } catch (Exception e) {
            return null;
        }
    }
}
