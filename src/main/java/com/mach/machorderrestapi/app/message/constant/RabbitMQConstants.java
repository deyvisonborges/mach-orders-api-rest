package com.mach.machorderrestapi.app.message.constant;

public class RabbitMQConstants {
	public static final String PAYMENT_REQUEST_QUEUE = "payment-request-queue";
	public static final String PAYMENT_RESPONSE_SUCCESS_QUEUE = "payment-response-success-queue";
	public static final String PAYMENT_RESPONSE_ERROR_QUEUE = "payment-response-error-queue";
	public static final String PAYMENT_REQUEST_EXCHANGE = "payment-request-exchange";
	public static final String PAYMENT_RESPONSE_SUCCESS_EXCHANGE = "payment-response-success-exchange";
	public static final String PAYMENT_RESPONSE_ERROR_EXCHANGE = "payment-response-error-exchange";

	private RabbitMQConstants() {
		throw new IllegalStateException("Utility class");
	}
}
