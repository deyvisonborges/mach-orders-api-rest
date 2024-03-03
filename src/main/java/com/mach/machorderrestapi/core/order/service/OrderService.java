package com.mach.machorderrestapi.core.order.service;

import com.mach.machorderrestapi.core.order.Order;
import com.mach.machorderrestapi.core.order.repository.OrderRepositoryContract;
import org.aspectj.weaver.ast.Or;

import java.util.UUID;

public class OrderService implements OrderServiceContract {
    private final OrderRepositoryContract orderRepositoryContract;

    public OrderService(OrderRepositoryContract orderRepositoryContract) {
        this.orderRepositoryContract = orderRepositoryContract;
    }

    @Override
    public void createOrder(Order.OrderRecord orderRecord) {
      	orderRepositoryContract.save(new Order(orderRecord));
    }
}
