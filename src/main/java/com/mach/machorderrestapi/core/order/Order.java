package com.mach.machorderrestapi.core.order;

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
  private final OrderStatus status;
  private final List<OrderItem> orderItems;
  private final BigDecimal total;
  private final UUID customerId;

  public Order(OrderRecord props) {
    super(props.id, props.active, props.createdAt, props.updatedAt);
    this.status = props.status;
    this.orderItems = props.orderItems.isEmpty() ? new ArrayList<>() : props.orderItems;
    this.total = props.total;
    this.customerId = props.customerId;
  }

  public record OrderRecord(
    UUID id,
    Boolean active,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    OrderStatus status,
    List<OrderItem> orderItems,
    BigDecimal total,
    UUID customerId
  ) {}

  public OrderStatus getStatus() {
    return status;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public static Order factory() {
    var order = new OrderRecord(UUID.randomUUID(),
      true,
      LocalDateTime.now(),
      LocalDateTime.now(),
      OrderStatus.ORDER_PLACED,
      new ArrayList<>(),
      null,
      null
    );
    return new Order(order);
  }

  private void addOrderItem(OrderItem.OrderItemRecord props) {
    this.orderItems.add(new OrderItem(props));
  }
}

