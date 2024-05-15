package com.example.Administrador_Sensores.rabbit_client;

import com.example.Administrador_Sensores.router.ActionRouterGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.utilities.intercambio.RequestFormat;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageListener {

    private final ActionRouterGateway actionRouterGateway;
    @Autowired
    private ObjectMapper objectMapper;

    public RabbitMQMessageListener(ActionRouterGateway actionRouterGateway) {
        this.actionRouterGateway = actionRouterGateway;
    }

    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receiveMessage(String content) throws JsonProcessingException {

        RequestFormat request = objectMapper.readValue(content, RequestFormat.class);
        System.out.println("--Recibiendo información del gateway de sensores");
        actionRouterGateway.route(request);
    }

}
