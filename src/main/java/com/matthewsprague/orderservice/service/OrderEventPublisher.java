package com.matthewsprague.orderservice.service;

import com.matthewsprague.orderservice.config.RabbitConfig;
import com.matthewsprague.orderservice.event.OrderCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisher {
    
    private final RabbitTemplate rabbitTemplate;
    
    public OrderEventPublisher(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
    public void publishOrderCreated(OrderCreatedEvent event){
        rabbitTemplate.convertAndSend(RabbitConfig.ORDER_EXCHANGE,
                                        "order.created",
                                        event);
    }
}
