package com.mach.machorderrestapi.app.message;

public class RabbitMQConstants {
	public static final String EMITTER_QUEUE_ORDER = "order.queue";
	public static final String EMITTER_QUEUE_ORDER_KEY = "order.key";
	public static final String LISTENER_QUEUE_PAYMENT = "payment.queue";

	private RabbitMQConstants() {
		throw new IllegalStateException("Utility class");
	}
}
