package com.example.GestionAlarmas.rabbit_client;

import com.mycompany.utilities.intercambio.RequestFormat;
import com.mycompany.utilities.intercambio.ResponseFormat;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageSend {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.invernadero.routing.key}")
    private String invernaderoRoutingKey;

    @Value("${rabbitmq.sensor.routing.key}")
    private String sensorRoutingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQMessageSend(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public ResponseFormat sendMessageInvernadero(RequestFormat request) {
        try {
            return (ResponseFormat) rabbitTemplate.convertSendAndReceive(
                    exchange,
                    invernaderoRoutingKey,
                    request);

        } catch (Exception e) {
            return null;
        }
    }

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
}
