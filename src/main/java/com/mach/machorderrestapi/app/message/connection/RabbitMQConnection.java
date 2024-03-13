package com.mach.machorderrestapi.app.message.connection;

import com.mach.machorderrestapi.app.message.constant.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {
	private final String EXCHANGE_NAME= "amqp.direct";
	private final AmqpAdmin amqpAdmin;

	public RabbitMQConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}

	private Queue queue(String queueName) {
		return new Queue(queueName, true, false, false);
	}

	private Binding directExchangeBiding(Queue queue, DirectExchange directExchange) {
		return new Binding(
			queue.getName(),
				Binding.DestinationType.QUEUE,
				directExchange.getName(),
				queue.getName(),
				null
		);
	}

	private DirectExchange directExchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	@PostConstruct
	public void onInit() {
		var paymentRequestQueue = this.queue(RabbitMQConstants.PAYMENT_REQUEST_QUEUE);
		var paymentResponseSuccessQueue = this.queue(RabbitMQConstants.PAYMENT_RESPONSE_SUCCESS_QUEUE);
		var paymentResponseErrorQueue = this.queue(RabbitMQConstants.PAYMENT_RESPONSE_ERROR_QUEUE);
		var directExchange = this.directExchange();
		var paymentRequestBinding = this.directExchangeBiding(paymentRequestQueue, directExchange);
		var paymentResponseSuccessBiding = this.directExchangeBiding(paymentResponseSuccessQueue, directExchange);
		var paymentResponseErrorBiding = this.directExchangeBiding(paymentResponseErrorQueue, directExchange);

		this.amqpAdmin.declareQueue(paymentRequestQueue);
		this.amqpAdmin.declareQueue(paymentResponseSuccessQueue);
		this.amqpAdmin.declareQueue(paymentResponseErrorQueue);
		this.amqpAdmin.declareExchange(directExchange);
		this.amqpAdmin.declareBinding(paymentRequestBinding);
		this.amqpAdmin.declareBinding(paymentResponseSuccessBiding);
		this.amqpAdmin.declareBinding(paymentResponseErrorBiding);
	}
}
