package com.mach.machorderrestapi.core.order.service;

import com.mach.machorderrestapi.core.order.Order;
import com.mach.machorderrestapi.core.order.repository.OrderRepositoryContract;

import java.util.UUID;

public class OrderService {
    private final OrderRepositoryContract orderRepositoryContract;

    public OrderService(OrderRepositoryContract orderRepositoryContract) {
        this.orderRepositoryContract = orderRepositoryContract;
    }

    public UUID createOrder() {
        final Order order =  Order.factory();
        orderRepositoryContract.save(order);
        return order.getId();
    }
}
