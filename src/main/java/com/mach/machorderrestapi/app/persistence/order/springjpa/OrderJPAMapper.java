package com.mach.machorderrestapi.app.persistence.order.springjpa;

import com.mach.machorderrestapi.core.artifact.order.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderJPAMapper {
	private OrderJPAMapper () {
		throw new IllegalArgumentException("Instance disabled");
	}

	public static OrderJPAEntity toJPAEntity(Order order) {
		List<OrderItemJPAEntity> orderItems = order.getOrderItems().stream()
			.map(item -> new OrderItemJPAEntity(
				item.getId(),
				item.getProductId(),
				item.getPrice(),
				item.getQuantity(),
				item.getActive(),
				null,
				order.getCreatedAt(),
				order.getUpdatedAt()))
			.collect(Collectors.toList());

		return new OrderJPAEntity(
			order.getId(),
			order.getStatus().toString(),
			order.getTotal(),
			order.getCustomerId(),
			order.getActive(),
			order.getCreatedAt(),
			order.getUpdatedAt(),
			orderItems
		);
	}
}
