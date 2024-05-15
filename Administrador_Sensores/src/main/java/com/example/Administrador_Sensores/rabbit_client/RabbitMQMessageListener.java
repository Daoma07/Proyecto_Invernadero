package com.example.Administrador_Sensores.rabbit_client;

import com.example.Administrador_Sensores.router.ActionRouterGateway;
import com.mycompany.utilities.intercambio.RequestFormat;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageListener {

    private final ActionRouterGateway actionRouterGateway;

    public RabbitMQMessageListener(ActionRouterGateway actionRouterGateway) {
        this.actionRouterGateway = actionRouterGateway;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(@Payload RequestFormat request) {
        System.out.println("--Recibiendo información del gateway de sensores");
        actionRouterGateway.route(request);
    }

}
