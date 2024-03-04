package com.mach.machorderrestapi.core.artifact.order.repository;

import com.mach.machorderrestapi.core.artifact.order.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepositoryContract {
	Optional<Order> findById(UUID id);
	void save(Order order);
}
