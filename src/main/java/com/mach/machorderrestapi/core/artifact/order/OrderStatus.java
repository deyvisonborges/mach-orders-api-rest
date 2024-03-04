package com.mach.machorderrestapi.core.artifact.order;

public enum OrderStatus {
    ORDER_PLACED("PEDIDO_REALIZADO"),
    PROCESSING("PROCESSANDO"),
    PREPARING_FOR_SHIPPING("PREPARANDO_PARA_ENVIO"),
    SENT("ENVIADO"),
    DELIVERED("ENTREGUE");

    private final String value;

    OrderStatus(String value) {
      this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}