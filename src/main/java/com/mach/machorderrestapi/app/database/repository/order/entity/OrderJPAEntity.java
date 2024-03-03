package com.mach.machorderrestapi.app.database.repository.order.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderJPAEntity implements Serializable {
	@Id
	@UuidGenerator
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", nullable = false)
	@JdbcTypeCode(SqlTypes.UUID)
	private UUID id;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private BigDecimal total;

	@Column(name = "customer_id", nullable = false)
	private UUID customerId;

	// Optional attributes
	private Boolean active;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<OrderItemJPAEntity> items;
}
