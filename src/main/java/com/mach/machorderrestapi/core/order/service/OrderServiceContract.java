package com.mach.machorderrestapi.core.order.service;

import com.mach.machorderrestapi.core.order.Order;

public interface OrderServiceContract {
	void createOrder(Order.OrderRecord orderRecord);
}
