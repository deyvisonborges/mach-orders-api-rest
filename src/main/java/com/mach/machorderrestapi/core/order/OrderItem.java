package com.mach.machorderrestapi.core.order;

import com.mach.machorderrestapi.common.base.BaseModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidades do Agregado: São objetos que fazem parte de um agregado e só podem ser acessados através
 * da raiz do agregado. Eles não têm significado ou existência fora do contexto do agregado. No seu caso,
 * os OrderItem são entidades do agregado Order, o que significa que eles são tratados como parte
 * integrante do Order e não são acessíveis diretamente fora do contexto do Order.
 * */
public class OrderItem extends BaseModel {
		private UUID productId;
		private BigDecimal price;
		private int quantity;

	public OrderItem(OrderItemRecord props) {
		super(props.id, props.active, props.createdAt, props.updatedAt);
		this.productId = props.productId;
		this.price = props.price;
		this.quantity = props.quantity;
	}

	public record OrderItemRecord(
			UUID id,
			Boolean active,
			LocalDateTime createdAt,
			LocalDateTime updatedAt,
			UUID productId,
			BigDecimal price,
			int quantity
	) {}
}