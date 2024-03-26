package com.mach.machorderrestapi.app.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class RabbitMQConstants {
	/*
	 * Order variables
	 * */
	@Value("${spring.rabbitmq.order.host}")
	public String ORDER_HOST;
	@Value("${spring.rabbitmq.order.username}")
	public String ORDER_USERNAME;
	@Value("${spring.rabbitmq.order.password}")
	public String ORDER_PASSWORD;
	@Value("${spring.rabbitmq.order.port}")
	public int ORDER_PORT;
	@Value("${spring.rabbitmq.order.exchange}")
	public String ORDER_EXCHANGE;
	@Value("${spring.rabbitmq.order.routing-key}")
	public String ORDER_ROUTING_KEY;
	@Value("${spring.rabbitmq.order.queue}")
	public String ORDER_QUEUE = "order-queue";

	/*
	* Payment variables
	* */
	@Value("${spring.rabbitmq.payment.host}")
	public String PAYMENT_HOST;
	@Value("${spring.rabbitmq.payment.username}")
	public String PAYMENT_USERNAME;
	@Value("${spring.rabbitmq.payment.password}")
	public String PAYMENT_PASSWORD;
	@Value("${spring.rabbitmq.payment.port}")
	public int PAYMENT_PORT;
	@Value("${spring.rabbitmq.payment.queue}")
	public String PAYMENT_QUEUE;

	public String getOrderHost() {
		return ORDER_HOST;
	}

	public String getOrderUsername() {
		return ORDER_USERNAME;
	}

	public String getOrderPassword() {
		return ORDER_PASSWORD;
	}

	public int getOrderPort() {
		return ORDER_PORT;
	}

	public String getOrderExchange() {
		return ORDER_EXCHANGE;
	}

	public String getOrderRoutingKey() {
		return ORDER_ROUTING_KEY;
	}

	public String getOrderQueue() {
		return ORDER_QUEUE;
	}

	public String getPaymentHost() {
		return PAYMENT_HOST;
	}

	public String getPaymentUsername() {
		return PAYMENT_USERNAME;
	}

	public String getPaymentPassword() {
		return PAYMENT_PASSWORD;
	}

	public int getPaymentPort() {
		return PAYMENT_PORT;
	}

	public String getPaymentQueue() {
		return PAYMENT_QUEUE;
	}
}
