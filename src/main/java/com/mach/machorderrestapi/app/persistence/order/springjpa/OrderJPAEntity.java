package com.mach.machorderrestapi.app.persistence.order.springjpa;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderJPAEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private UUID id;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private BigDecimal total;

	@Column(name = "customer_id", nullable = false)
	private UUID customerId;

	@Column(name = "payment_id", nullable = false)
	private UUID paymentId;

	private Boolean active;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<OrderItemJPAEntity> items = new ArrayList<>();

	public OrderJPAEntity() {
	}

	public OrderJPAEntity(UUID id, String status, BigDecimal total, UUID customerId, UUID paymentId, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, List<OrderItemJPAEntity> items) {
		this.id = id;
		this.status = status;
		this.total = total;
		this.customerId = customerId;
		this.paymentId = paymentId;
		this.active = active;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.items = items;
	}
}
