package com.matthewsprague.orderservice.listener;

import com.matthewsprague.orderservice.config.RabbitConfig;
import com.matthewsprague.orderservice.entity.OrderStatus;
import com.matthewsprague.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.matthewsprague.orderservice.event.InventoryReservedEvent;
@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryReservedListener {
    private final OrderRepository orderRepository;
    
    @RabbitListener(queues = RabbitConfig.ORDER_RESERVED_QUEUE)
    public void handleInventoryReserved(InventoryReservedEvent event){
        log.info("Received InventoryReservedEvent for order {} with status {}", event.orderId(), event.status());
        orderRepository.findById(event.orderId())
                .ifPresent(order -> {
                    order.setStatus(OrderStatus.valueOf(event.status()));
                    orderRepository.save(order);
                });
    }
}
