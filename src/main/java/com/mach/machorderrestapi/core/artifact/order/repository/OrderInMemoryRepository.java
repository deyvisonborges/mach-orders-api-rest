package com.mach.machorderrestapi.core.artifact.order.repository;

import com.mach.machorderrestapi.common.base.InMemoryBaseRepository;
import com.mach.machorderrestapi.core.artifact.order.Order;

import java.util.Optional;
import java.util.UUID;

public class OrderInMemoryRepository extends InMemoryBaseRepository<Order> implements OrderRepositoryContract {
	@Override
	public Optional<Order> findById(UUID id) {
		return items.stream()
			.filter(order -> order.getId().equals(id))
			.findFirst();
	}

	@Override
	public void save(Order order) {
		items.add(order);
	}
}
