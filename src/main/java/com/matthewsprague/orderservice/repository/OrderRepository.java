package com.matthewsprague.orderservice.repository;

import com.matthewsprague.orderservice.entity.Order;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, UUID> {
    
}
