package com.mach.machorderrestapi.artifacts.order.repository;

import com.mach.machorderrestapi.artifacts.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {}