package com.example.restapi.Domain;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    private @Id @GeneratedValue Long id;

    private String order_date;

    private Double order_total;

    private String order_status;

    @ManyToOne()
    @JoinColumn(name="customers_id", nullable=false)
    private Customer customer;

    public Order(){};

    public Order(String order_date,Double order_total,String order_status){
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id) && Objects.equals(order_date, order.order_date) && Objects.equals(order_total, order.order_total) && Objects.equals(order_status, order.order_status) && Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order_date, order_total, order_status, customer);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_date='" + order_date + '\'' +
                ", order_total=" + order_total +
                ", order_status='" + order_status + '\'' +
                ", customer=" + customer +
                '}';
    }
}
