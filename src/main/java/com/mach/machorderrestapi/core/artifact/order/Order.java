package com.mach.machorderrestapi.core.artifact.order;

import com.mach.machorderrestapi.common.base.BaseModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Agregado: Um agregado é uma coleção de objetos relacionados que são tratados
 * como uma única unidade. Um agregado geralmente possui uma raiz de agregado que
 * atua como ponto de entrada para o acesso e a manipulação de outros objetos dentro
 * do agregado. No seu exemplo, o Order é o agregado e os OrderItem são parte desse agregado.
 */

public class Order extends BaseModel {
  private OrderStatus status;
  private List<OrderItem> orderItems = new ArrayList<>();
  private BigDecimal total;
  private UUID customerId;

  public Order(OrderStatus status, BigDecimal total, UUID customerId) {
    super(UUID.randomUUID(), true, LocalDateTime.now(), LocalDateTime.now());
    this.status = status;
    this.total = total;
    this.customerId = customerId;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public UUID getCustomerId() {
    return customerId;
  }

  public void addOrderItem(OrderItem orderItem) {
    orderItems.add(orderItem);
  }

  public void removeOrderItem(OrderItem orderItem) {
    orderItems.remove(orderItem);
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
