package com.mach.machorderrestapi.app.database.repository.order;

import com.mach.machorderrestapi.app.database.repository.order.entity.OrderItemJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemJPARepository extends JpaRepository<OrderItemJPAEntity, UUID> { }
