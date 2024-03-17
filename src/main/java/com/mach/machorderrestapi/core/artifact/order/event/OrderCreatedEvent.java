package com.mach.machorderrestapi.core.artifact.order.event;

import com.mach.machorderrestapi.app.message.RabbitMqService;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEvent {
	private final RabbitMqService rabbitMqService;

	public OrderCreatedEvent(RabbitMqService rabbitMqService) {
		this.rabbitMqService = rabbitMqService;
	}

	public void execute(Object data) {
		this.rabbitMqService.sendMessage("order-queue", data);
	}
}