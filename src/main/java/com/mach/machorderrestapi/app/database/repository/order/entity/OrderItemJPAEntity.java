package com.mach.machorderrestapi.app.database.repository.order.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItemJPAEntity implements Serializable {
	@Id
	@UuidGenerator
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", nullable = false)
	@JdbcTypeCode(SqlTypes.UUID)
	private UUID id;

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id", updatable = false, unique = true, nullable = false)
//	private UUID id;

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
