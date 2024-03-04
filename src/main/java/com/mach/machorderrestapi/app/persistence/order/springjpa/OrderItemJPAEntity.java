package com.mach.machorderrestapi.app.persistence.order.springjpa;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItemJPAEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private UUID id;

	@Column(name = "product_id", nullable = false)
	private UUID productId;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private int quantity;

	// Optional attributes
	private Boolean active;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = true)
	private OrderJPAEntity order;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
