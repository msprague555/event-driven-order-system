package com.matthewsprague.orderservice.event;

public record InventoryReservedEvent(
        String orderId,
        String status){}
