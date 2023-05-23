package com.example.restapi.Domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class Product {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private String name;

    private String category;

    private Double reviews;

    @OneToMany(mappedBy = "product")
    List<OrderProduct> order_products;

    public List<OrderProduct> getOrder_products() {
        return order_products;
    }

    public void setOrder_products(List<OrderProduct> order_products) {
        this.order_products = order_products;
    }

    public Product(){};

    public Product(String name,String category, Double reviews){
        this.name = name;
        this.category = category;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getReviews() {
        return reviews;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setReviews(Double reviews) {
        this.reviews = reviews;
    }

}


