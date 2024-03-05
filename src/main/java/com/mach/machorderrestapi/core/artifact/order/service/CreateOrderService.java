package com.mach.machorderrestapi.core.artifact.order.service;

import com.mach.machorderrestapi.common.base.ServiceContract;
import com.mach.machorderrestapi.core.artifact.order.Order;
import com.mach.machorderrestapi.core.artifact.order.OrderStatus;
import com.mach.machorderrestapi.core.artifact.order.repository.OrderInMemoryRepository;
import com.mach.machorderrestapi.core.artifact.order.repository.OrderRepositoryContract;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderService extends ServiceContract<Order.OrderRecord, Order> {
	private final OrderRepositoryContract orderRepositoryContract;

	public CreateOrderService() {
		this.orderRepositoryContract = new OrderInMemoryRepository();
	}

	@Override
	public Order execute(Order.OrderRecord input) {
		var order = new Order(input);
		order.setStatus(OrderStatus.ORDER_PLACED);
		orderRepositoryContract.save(order);
		return order;
	}
}
