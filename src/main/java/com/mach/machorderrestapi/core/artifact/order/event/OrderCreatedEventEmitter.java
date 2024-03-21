package com.mach.machorderrestapi.core.artifact.order.event;

import com.mach.machorderrestapi.app.message.RabbitMqService;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventEmitter {
	private final RabbitMqService rabbitMqService;

	public OrderCreatedEventEmitter(RabbitMqService rabbitMqService) {
		this.rabbitMqService = rabbitMqService;
	}

	public void execute(Object data) {
		this.rabbitMqService.sendMessage("order-queue", data);
	}
}