package com.mach.machorderrestapi.artifacts.order.repository;

import com.mach.machorderrestapi.artifacts.order.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepositoryContract {
    Optional<Order> findById(UUID id);
    Order save(Order order);
}
