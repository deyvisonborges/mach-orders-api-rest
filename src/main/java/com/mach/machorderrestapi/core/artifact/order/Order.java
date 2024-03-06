package com.mach.machorderrestapi.core.artifact.order;

import com.mach.machorderrestapi.common.base.BaseModel;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Agregado: Um agregado é uma coleção de objetos relacionados que são tratados
 * como uma única unidade. Um agregado geralmente possui uma raiz de agregado que
 * atua como ponto de entrada para o acesso e a manipulação de outros objetos dentro
 * do agregado. No seu exemplo, o Order é o agregado e os OrderItem são parte desse agregado.
 */

public class Order extends BaseModel {
  private OrderStatus status;
  private List<OrderItem> orderItems;
  private BigDecimal total;
  private UUID customerId;

  public Order(OrderRecord props) {
    super(
      props.id().orElse(UUID.randomUUID()),
      props.active().orElse(true),
      props.createdAt().orElse(LocalDateTime.now()),
      props.updatedAt().orElse(LocalDateTime.now())
    );
    this.status = props.status;
    this.orderItems = props.orderItems.isEmpty() ? new ArrayList<>() : props.orderItems;
    this.total = props.total;
    this.customerId = props.customerId;
  }

  public record OrderRecord(
    Optional<UUID> id,
    Optional<Boolean> active,
    Optional<LocalDateTime> createdAt,
    Optional<LocalDateTime> updatedAt,
    OrderStatus status,
    List<OrderItem> orderItems,
    BigDecimal total,
    UUID customerId
  ) {}

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

  public static Order factory() {
    var order = new OrderRecord(
      Optional.of(UUID.randomUUID()),
      Optional.of(true),
      Optional.of(LocalDateTime.now()),
      Optional.of(LocalDateTime.now()),
      OrderStatus.ORDER_PLACED,
      new ArrayList<>(),
      null,
      null
    );
    return new Order(order);
  }

  public void addOrderItem(OrderItem props) {
    this.orderItems.add(props);
  }

}

