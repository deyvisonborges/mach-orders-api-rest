package com.mach.machorderrestapi.core.order.repository;

import com.mach.machorderrestapi.core.order.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepositoryContract {
	Optional<Order> findById(UUID id);
	void save(Order order);
}
