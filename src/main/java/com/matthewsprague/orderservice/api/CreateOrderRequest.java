package com.matthewsprague.orderservice.api;

import java.math.BigDecimal;

public record CreateOrderRequest (
        String customerId,
        BigDecimal totalAmount    
){}
