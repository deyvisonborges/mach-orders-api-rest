package com.mach.machorderrestapi.artifacts.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
  private UUID id;
  private OrderStatus status;
  private List<OrderItem> orderItems;
  private BigDecimal total;

  public Order(OrderProps props) {
    this.id = props.getId();
    this.status = props.getStatus();
    this.orderItems = props.getOrderItems();
    this.total = props.getTotal();
  }

  public static class OrderProps {
    private UUID id;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private BigDecimal total;

    public OrderProps(UUID id, OrderStatus status, List<OrderItem> orderItems, BigDecimal total) {
      this.id = id;
      this.status = status;
      this.orderItems = orderItems;
      this.total = total;
    }

    public UUID getId() {
      return id;
    }

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

  // Getters para os campos
  public UUID getId() {
    return id;
  }

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

enum OrderStatus {
  // Definir os diferentes status de pedido, se necessário
}

class OrderItem {
  // Definir a classe OrderItem, se necessário
}
