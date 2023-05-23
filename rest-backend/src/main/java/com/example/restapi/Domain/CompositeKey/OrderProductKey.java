package com.example.restapi.Domain.CompositeKey;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderProductKey implements Serializable {

    @Column(name = "orderId")
    Long orderId;

    @Column(name = "productId")
    Long productId;

    public OrderProductKey(){};

    public OrderProductKey(Long order_id,Long product_id) {
        this.orderId = order_id;
        this.productId = product_id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setOrderId(Long order_id) {
        this.orderId = order_id;
    }

    public void setProductId(Long product_id) {
        this.productId = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProductKey orderProductKey)) return false;
        return Objects.equals(orderId, orderProductKey.orderId) && Objects.equals(productId, orderProductKey.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
}
