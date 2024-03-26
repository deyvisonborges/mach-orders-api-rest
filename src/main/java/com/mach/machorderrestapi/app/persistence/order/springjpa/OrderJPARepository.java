package com.mach.machorderrestapi.app.persistence.order.springjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderJPARepository extends JpaRepository<OrderJPAEntity, UUID> {
}
