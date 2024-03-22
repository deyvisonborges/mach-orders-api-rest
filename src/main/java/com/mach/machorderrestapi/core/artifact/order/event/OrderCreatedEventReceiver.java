package com.mach.machorderrestapi.core.artifact.order.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

class PaymentDTO {
	private String paymentId;
	private String paymentMethod;
	private double value;
	private LocalDateTime paidIn;
	private String status;
	private String paymentType;
	private String orderId;
	private String customerId;

	public PaymentDTO() {
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setPaidIn(LocalDateTime paidIn) {
		this.paidIn = paidIn;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public double getValue() {
		return value;
	}

	public LocalDateTime getPaidIn() {
		return paidIn;
	}

	public String getStatus() {
		return status;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getCustomerId() {
		return customerId;
	}
}

@Component
public class OrderCreatedEventReceiver {
	private final ObjectMapper mapper;

	public OrderCreatedEventReceiver(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public void receiveMessage(byte[] message) {
		String messageString = new String(message);

		PaymentDTO paymentDTO;

		try {
			paymentDTO = mapper.readValue(messageString, PaymentDTO.class);
			System.out.println("Payment ID: " + paymentDTO.getPaymentId());
			System.out.println("Payment Type: " + paymentDTO.getPaymentType());
			System.out.println("Order ID: " + paymentDTO.getOrderId());
			System.out.println("Customer ID: " + paymentDTO.getCustomerId());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
