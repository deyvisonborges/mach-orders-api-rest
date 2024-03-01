package com.mach.machorderrestapi.core.order.repository;

import com.mach.machorderrestapi.common.base.InMemoryBaseRepository;
import com.mach.machorderrestapi.core.order.Order;
import org.aspectj.weaver.ast.Or;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class OrderInMemoryRepository extends InMemoryBaseRepository<Order> implements OrderRepositoryContract {
	@Override
	public Optional<Order> findById(UUID id) {
		var hasOrder = items.stream().findFirst().filter(i -> i.getId().equals(id));

		if(Objects.isNull(hasOrder)) {
			throw new RuntimeException("Not found order");
		}
		return hasOrder;
	}

	@Override
	public void save(Order order) {
		this.items.add(order);
	}
}
