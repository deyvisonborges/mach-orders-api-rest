package com.mach.machorderrestapi.core.order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItem(UUID productId, BigDecimal price, int quantity) { }