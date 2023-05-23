package com.example.restapi.Domain;

import com.example.restapi.Domain.CompositeKey.OrderProductKey;
import jakarta.persistence.*;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct {
    @EmbeddedId
    private OrderProductKey id;

    @ManyToOne
    @MapsId("orderId")
    Order order;

    @ManyToOne
    @MapsId("productId")
    Product product;

    private Integer size;

    private Double subtotal;

    public OrderProduct(){};

    public OrderProduct(Order order,Product product){
        this.order = order;
        this.product = product;
        this.id = new OrderProductKey(order.getId(),product.getId());
    }

    public OrderProductKey getId(){return this.id;}

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getSize() {
        return size;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setId(OrderProductKey id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
