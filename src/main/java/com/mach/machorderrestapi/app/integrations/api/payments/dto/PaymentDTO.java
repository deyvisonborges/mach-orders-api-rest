package com.mach.machorderrestapi.app.integrations.api.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * ---------------------------
 * == Example credit card ==
 * # 234254525 (payment id)
 * 6x R$ 43,32
 * Mastercard **** 1313
 * 5 de setembro de 2021
 * Pagamento aprovado
 * ---------------------------
 * ---------------------------
 * ---------------------------
 * == Example PIX ==
 * # 234254525 (payment id)
 * R$ 357,57
 * Pix
 * 19 de julho de 2023
 * Pagamento aprovado
 * ---------------------------
 */
public record PaymentDTO(
		@JsonProperty("payment_id")
		String paymentId,

		@JsonProperty("payment_method")
		String paymentMethod,

		String value,
		@JsonProperty("paid_in")
		LocalDateTime paidIn,
		String status
) { }
