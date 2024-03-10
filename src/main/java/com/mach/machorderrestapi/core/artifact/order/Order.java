package com.mach.machorderrestapi.core.artifact.order;

import com.mach.machorderrestapi.common.base.BaseModel;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Agregado: Um agregado é uma coleção de objetos relacionados que são tratados
 * como uma única unidade. Um agregado geralmente possui uma raiz de agregado que
 * atua como ponto de entrada para o acesso e a manipulação de outros objetos dentro
 * do agregado. No seu exemplo, o Order é o agregado e os OrderItem são parte desse agregado.
 */
public class Order extends BaseModel {
  private OrderStatus status;
  private Set<OrderItem> items;
  private UUID customerId;
  private Set<String> paymentsIds;
  private double subTotal;
  private double shippingFee;
  private double discount;
  private double total;

  public Order(UUID id, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, OrderStatus status, Set<OrderItem> items, UUID customerId, Set<String> paymentsIds, double subTotal, double shippingFee, double discount, double total) {
    super(id, active, createdAt, updatedAt);
    this.status = status;
    this.items = items;
    this.customerId = customerId;
    this.paymentsIds = paymentsIds;
    this.subTotal = subTotal;
    this.shippingFee = shippingFee;
    this.discount = discount;
    this.total = total;
  }

  /**
   * Getters
   */
  public OrderStatus getStatus() {
    return status;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public UUID getCustomerId() {
    return customerId;
  }

  public Set<String> getPaymentsIds() {
    return paymentsIds;
  }

  public double getSubTotal() {
    return subTotal;
  }

  public double getShippingFee() {
    return shippingFee;
  }

  public double getDiscount() {
    return discount;
  }

  public double getTotal() {
    return total;
  }

  /**
   * Setters
   */
  public void addOrderItem(OrderItem orderItem) {
    items.add(orderItem);
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
