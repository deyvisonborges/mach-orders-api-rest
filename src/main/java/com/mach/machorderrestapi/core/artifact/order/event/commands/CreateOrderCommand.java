package com.mach.machorderrestapi.core.artifact.order.event.commands;

import com.mach.machorderrestapi.core.artifact.order.OrderStatus;

import java.util.Set;
import java.util.UUID;

public record CreateOrderCommand(
	OrderStatus status,
	Set<String> items,
	UUID customerId,
	Set<String> paymentsIds,
	double subTotal,
	double shippingFee,
	double discount,
	double total
){}
