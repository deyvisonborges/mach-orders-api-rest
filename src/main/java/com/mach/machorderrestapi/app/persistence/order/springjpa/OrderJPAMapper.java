package com.mach.machorderrestapi.app.persistence.order.springjpa;

import com.mach.machorderrestapi.core.artifact.order.Order;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderJPAMapper {
	private OrderJPAMapper () {
		throw new IllegalArgumentException("Instance disabled");
	}

	public static OrderJPAEntity toJPAEntity(Order order) {
		List<OrderItemJPAEntity> orderItems = order.getItems().stream()
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

		var orderJPAEntity = new OrderJPAEntity(
			order.getId(),
			order.getActive(),
			order.getCreatedAt(),
			order.getUpdatedAt(),
			order.getStatus(),
			(Set<OrderItemJPAEntity>) orderItems,
			order.getCustomerId(),
			order.getPaymentsIds(),
			order.getSubTotal(),
			order.getShippingFee(),
			order.getDiscount(),
			order.getTotal()
		);

		orderItems.forEach(item -> item.setOrder(orderJPAEntity));

		return orderJPAEntity;
	}
}
