package com.mach.machorderrestapi.artifacts.order;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

abstract class OrderProps {
  protected Long id;
  protected double total;
  protected String customerId;
  protected String orderStatus;
  protected LocalDateTime orderPlacedIn;
}

public class Order extends OrderProps implements Serializable {  
  public Order() {
    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  protected double total;
  protected String customerId;
  protected String orderStatus;
  protected LocalDateTime orderPlacedIn;
  
  public void setId(Long id) {
    super.id = id;
  }

  public Long getId() {
    return super.id;
  }
}
