package com.mach.machorderrestapi.artifacts.order;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {
    private UUID productId;
    private BigDecimal price;

    public OrderItem(UUID productId, BigDecimal price) {
        this.productId = productId;
        this.price = price;
    }
}