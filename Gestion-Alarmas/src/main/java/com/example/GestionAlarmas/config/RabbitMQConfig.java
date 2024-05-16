package com.example.GestionAlarmas.config;

import java.util.List;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Value("${fanout.queue}")
    private String queue;

    @Value("${fanout.exchange}")
    private String exchange;

    @Bean
    public Queue fanoutQueue() {
        return new Queue(queue, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(exchange);
    }

    @Bean
    public Binding fanoutBinding(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }

    @Bean
    public SimpleMessageConverter converter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(List.of("com.mycompany.utilities.dto.*",
                "com.mycompany.utilities.intercambio.*",
                "com.mycompany.utilities.formatoGateway.*"));
        return converter;
    }

}
