package com.matthewsprague.orderservice.event;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderCreatedEvent (
        String orderId,
        String customerId,
        BigDecimal totalAmount,
        Instant createdAt
){}
