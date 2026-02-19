package com.matthewsprague.orderservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {
    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_RESERVED_QUEUE = "order.reserved.queue";
    //Inventory exchange to update order status based on inventory event
    public static final String INVENTORY_EXCHANGE = "inventory.exchange";
    public static final String INVENTORY_RESERVED_KEY = "inventory.reserved";
    
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    TopicExchange inventoryExchange(){
        return new TopicExchange(INVENTORY_EXCHANGE);
    }
    
    @Bean
    TopicExchange orderExchange(){
        return new TopicExchange(ORDER_EXCHANGE);
    }
    
    @Bean
    public Queue orderReservedQueue(){
        return new Queue(ORDER_RESERVED_QUEUE);
    }
    
    @Bean
    public Binding orderCreatedBinding(){
        return BindingBuilder.bind(orderReservedQueue())
                             .to(inventoryExchange())
                             .with(INVENTORY_RESERVED_KEY);
    }
}
