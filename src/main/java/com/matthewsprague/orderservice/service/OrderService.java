package com.matthewsprague.orderservice.service;

import com.matthewsprague.orderservice.entity.Order;
import com.matthewsprague.orderservice.entity.OrderStatus;
import com.matthewsprague.orderservice.api.CreateOrderRequest;
import com.matthewsprague.orderservice.event.OrderCreatedEvent;
import com.matthewsprague.orderservice.repository.OrderRepository;
import java.time.Instant;
import org.springframework.stereotype.Service;


@Service
public class OrderService {
    
    private final OrderRepository repo;
    private final OrderEventPublisher publisher;
    
    public OrderService(OrderRepository repository, OrderEventPublisher orderEventPublisher){
        this.repo = repository;
        this.publisher = orderEventPublisher;
    }
    public Order createOrder(CreateOrderRequest req){
        Order order = new Order();
        order.setCustomerId(req.customerId());
        order.setTotalAmount(req.totalAmount());
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(Instant.now());
        
        Order saved = repo.save(order);
        
        publisher.publishOrderCreated(new OrderCreatedEvent(
                                        saved.getId(),
                                        saved.getCustomerId(),
                                        saved.getTotalAmount(),
                                        saved.getCreatedAt()));
        return saved;
    }
}
