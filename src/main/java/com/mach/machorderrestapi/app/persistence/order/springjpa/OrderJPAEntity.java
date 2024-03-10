package com.mach.machorderrestapi.app.persistence.order.springjpa;

import com.mach.machorderrestapi.core.artifact.order.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders")
public class OrderJPAEntity implements Serializable {
	/*
	* Default attributes
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
	@Column(nullable = false)
	private OrderStatus status;

	@OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Set<OrderItemJPAEntity> items = new HashSet<>();

	@Column(name = "customer_id", nullable = false)
	private UUID customerId;

	@Column(name = "payments_ids", nullable = false)
	private Set<String> paymentsIds = new HashSet<>();

	@Column(name = "subtotal", nullable = false)
	private double subTotal;

	@Column(name = "shipping_fee", nullable = false)
	private double shippingFee;

	@Column(nullable = false)
	private double discount;

	@Column(nullable = false)
	private double total;

	public OrderJPAEntity() {
	}

	public OrderJPAEntity(UUID id, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, OrderStatus status, Set<OrderItemJPAEntity> items, UUID customerId, Set<String> paymentsIds, double subTotal, double shippingFee, double discount, double total) {
		this.id = id;
		this.active = active;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
		this.items = items;
		this.customerId = customerId;
		this.paymentsIds = paymentsIds;
		this.subTotal = subTotal;
		this.shippingFee = shippingFee;
		this.discount = discount;
		this.total = total;
	}
}
