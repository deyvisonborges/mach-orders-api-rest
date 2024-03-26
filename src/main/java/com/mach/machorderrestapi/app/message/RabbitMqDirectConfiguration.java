package com.mach.machorderrestapi.app.message;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqDirectConfiguration {
	private final AmqpAdmin amqpAdmin;
	private final RabbitMQConstants rabbitMQConstants;

	public RabbitMqDirectConfiguration(AmqpAdmin amqpAdmin, RabbitMQConstants rabbitMQConstants) {
		this.amqpAdmin = amqpAdmin;
		this.rabbitMQConstants = rabbitMQConstants;
	}

	public Queue queue (String queueName) {
		return new Queue(queueName, true, false, false);
	}

	public DirectExchange directOrderExchange() {
		return new DirectExchange(this.rabbitMQConstants.getOrderExchange());
	}

	public Binding bindQueueToExchange(Queue queue, DirectExchange exchange) {
		return new Binding(
			queue.getName(),
			Binding.DestinationType.QUEUE,
			exchange.getName(),
			queue.getName(),
			null
		);
	}

	@PostConstruct
	private void addQueues() {
		var orderQueue = this.queue(this.rabbitMQConstants.getOrderQueue());
		var orderExchange = this.directOrderExchange();
		var orderBinding = this.bindQueueToExchange(orderQueue, directOrderExchange());

		this.amqpAdmin.declareQueue(orderQueue);
		this.amqpAdmin.declareExchange(orderExchange);
		this.amqpAdmin.declareBinding(orderBinding);
	}
}
