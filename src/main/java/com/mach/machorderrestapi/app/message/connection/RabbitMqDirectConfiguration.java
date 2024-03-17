package com.mach.machorderrestapi.app.message.connection;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqDirectConfiguration {
	@Value("${rabbitmq.exchange.order}")
	private String orderExchange;

	private final AmqpAdmin amqpAdmin;

//	public Queue orderQueue() {/*
//	 * Parâmetros:
//	 * - @param nome: Nome da fila.
//	 * - @param durable: Indica se a fila é durável (persistente).
//	 * - @param exclusive: Indica se a fila é exclusiva para uma conexão.
//	 * - @param autoDelete: Indica se a fila é excluída automaticamente quando não é mais utilizada.
//	 */
//		return new Queue("orders.v1.order-created", true, false, false);
//	}

	public RabbitMqDirectConfiguration(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}

	public Queue queue (String queueName) {
		return new Queue(queueName, true, false, false);
	}

	public DirectExchange directOrderExchange() {
		return new DirectExchange(orderExchange);
	}

	public Binding bindQueueToExchange(Queue queue, DirectExchange exchange) {
		return new Binding(
			queue.getName(),
			Binding.DestinationType.QUEUE,
			exchange.getName(),
			queue.getName(), // routing key aqui, é o próprio nome da fila
			null
		);
	}

	@PostConstruct
	private void addQueues() {
		var orderQueue = this.queue("order-queue");
		var orderExchange = this.directOrderExchange();
		var orderBinding = this.bindQueueToExchange(orderQueue, directOrderExchange());

		this.amqpAdmin.declareQueue(orderQueue);
		this.amqpAdmin.declareExchange(orderExchange);
		this.amqpAdmin.declareBinding(orderBinding);
	}
}
