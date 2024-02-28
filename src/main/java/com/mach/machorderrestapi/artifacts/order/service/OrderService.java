package com.mach.machorderrestapi.artifacts.order.service;

import com.mach.machorderrestapi.artifacts.order.Order;
import com.mach.machorderrestapi.artifacts.order.repository.OrderRepositoryContract;

import java.util.UUID;

public class OrderService {
    private final OrderRepositoryContract orderRepositoryContract;

    public OrderService(OrderRepositoryContract orderRepositoryContract) {
        this.orderRepositoryContract = orderRepositoryContract;
    }

    public UUID createOrder() {
        final Order order =  new Order();
        orderRepositoryContract.save(order);
        return order.getId();
    }
}
