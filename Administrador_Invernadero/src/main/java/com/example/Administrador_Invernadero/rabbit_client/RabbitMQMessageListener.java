package com.example.Administrador_Invernadero.rabbit_client;

import com.example.Administrador_Invernadero.router.ActionRouter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.utilities.intercambio.RequestFormat;
import com.mycompany.utilities.intercambio.ResponseFormat;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageListener {

    private final ActionRouter actionRouter;

    @Autowired
    private ObjectMapper objectMapper;

    public RabbitMQMessageListener(ActionRouter actionRouter) {
        this.actionRouter = actionRouter;
    }

    @RabbitListener(queues = {"${rabbitmq.invernadero.queue.name}"})
    public ResponseFormat consume(@Payload RequestFormat request) throws Exception {
        ResponseFormat responseFormat = new ResponseFormat();
        try {
            responseFormat = actionRouter.route(request);
        } catch (Exception ex) {
            responseFormat.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseFormat.setContent(ex.getMessage());
        }
        return responseFormat;
    }

}