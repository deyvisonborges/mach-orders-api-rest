package com.mach.machorderrestapi.core.artifact.order;

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

	public OrderItem(UUID productId, BigDecimal price, int quantity) {
		super(UUID.randomUUID(), true, LocalDateTime.now(), LocalDateTime.now());
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}

	public UUID getProductId() {
		return productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}
}
