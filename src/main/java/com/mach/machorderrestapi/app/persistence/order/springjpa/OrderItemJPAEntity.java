package com.mach.machorderrestapi.app.persistence.order.springjpa;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItemJPAEntity implements Serializable {
	/*
	* Default Attributes
	* */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private UUID id;

	private Boolean active;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	/*
	* Required Attributes
	* */
	@Column(name = "product_id", nullable = false)
	private UUID productId;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private int quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = true)
	private OrderJPAEntity order;

	public OrderItemJPAEntity() {
	}

	public OrderItemJPAEntity(UUID id, UUID productId, BigDecimal price, int quantity, Boolean active, OrderJPAEntity order, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.active = active;
		this.order = order;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public void setOrder(OrderJPAEntity order) {
		this.order = order;
	}
}
