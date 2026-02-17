package com.matthewsprague.orderservice.controller;

import com.matthewsprague.orderservice.entity.Order;
import com.matthewsprague.orderservice.api.CreateOrderRequest;
import com.matthewsprague.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    private final OrderService service;
    
    public OrderController(OrderService service){
        this.service = service;
    }
    
    @PostMapping
    public Order create(@RequestBody CreateOrderRequest req){
        return service.createOrder(req);
    }
}
