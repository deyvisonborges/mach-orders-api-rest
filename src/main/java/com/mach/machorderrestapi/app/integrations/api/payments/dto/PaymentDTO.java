package com.mach.machorderrestapi.app.integrations.api.payments.dto;

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
		String paymentId,
		String paymentMethod,
		String value,
		LocalDateTime paidIn,
		String status
) { }
