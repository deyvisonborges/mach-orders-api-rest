package com.mach.machorderrestapi.artifacts.order;

import com.mach.machorderrestapi.common.base.BaseModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order extends BaseModel {
  private final OrderStatus status;
  private final List<OrderItem> orderItems;
  private final BigDecimal total;

  public Order(OrderProps props) {
    super(props.id, props.active, props.createdAt, props.updatedAt);
    this.status = props.status();
    this.orderItems = props.orderItems();
    this.total = props.total();
  }

  public record OrderProps(
    UUID id,
    Boolean active,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    OrderStatus status,
    List<OrderItem> orderItems,
    BigDecimal total
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
}

