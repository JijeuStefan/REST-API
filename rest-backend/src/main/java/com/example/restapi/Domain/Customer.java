package com.example.restapi.Domain;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


@Entity
@Table(name = "CUSTOMER")
public class Customer {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String first_name;
    private String second_name;
    private String email;
    private String gender;
    private int age;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Order> orders;

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Customer() {}

    public Customer(String first_name, String second_name, String email,String gender,int age){
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public void setId(Long id) {this.id = id;}


    public void setFirst_name(String first_name) {this.first_name = first_name;}


    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setName(String name) {
        this.first_name = name.split(" ")[0];
        this.second_name = name.split(" ")[1];
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirst_name() {return first_name;}

    public Long getId() {return id;}


    public String getSecond_name() {
        return second_name;
    }

    public String getName() {return this.first_name + " " + this.second_name;}

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
}
