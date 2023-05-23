package com.example.restapi.Domain;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ORDERS")
public class Order {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private String order_date;

    private Double order_total;

    private String order_status;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    List<OrderProduct> order_products;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderProduct> getOrder_products() {
        return order_products;
    }

    public void setOrder_products(List<OrderProduct> order_products) {
        this.order_products = order_products;
    }

    public Order(){};

    public Order(String order_date, Double order_total, String order_status){
        this.order_date = order_date;
        this.order_total = order_total;
        this.order_status = order_status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setOrder_total(Double order_total) {
        this.order_total = order_total;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Long getId() {
        return id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public Double getOrder_total() {
        return order_total;
    }

    public String getOrder_status() {
        return order_status;
    }
}
