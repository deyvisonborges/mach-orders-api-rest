package com.mach.machorderrestapi.core.artifact.order.event;

import com.mach.machorderrestapi.core.artifact.order.OrderStatus;

import java.util.UUID;

public class OrderEvent {
	private UUID id;
	private OrderStatus status;

	public OrderEvent(UUID id, OrderStatus status) {
		this.id = id;
		this.status = status;
	}

	public UUID getId() {
		return id;
	}

	public OrderStatus getStatus() {
		return status;
	}
}
