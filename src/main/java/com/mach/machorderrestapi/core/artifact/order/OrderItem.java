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

	public OrderItem(UUID id, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, UUID productId, BigDecimal price, int quantity) {
		super(id, active, createdAt, updatedAt);
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

	public static class OrderItemBuilder {
		private UUID id;
		private Boolean active;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
		private UUID productId;
		private BigDecimal price;
		private int quantity;

		public OrderItemBuilder() {}

		public OrderItemBuilder id(UUID id) {
			this.id = id;
			return this;
		}

		public OrderItemBuilder active(Boolean active) {
			this.active = active;
			return this;
		}

		public OrderItemBuilder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public OrderItemBuilder updatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
			return this;
		}

		public OrderItemBuilder productId(UUID productId) {
			this.productId = productId;
			return this;
		}

		public OrderItemBuilder price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public OrderItemBuilder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public OrderItem build() {
			return new OrderItem(id, active, createdAt, updatedAt, productId, price, quantity);
		}
	}
}
