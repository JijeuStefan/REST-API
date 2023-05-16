package com.example.restapi.Domain;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;


@Entity
@Table(name = "customer")
public class Customer {
    private @Id @GeneratedValue Long id;
    private String first_name;
    private String second_name;
    private String email;
    private String gender;
    private int age;

    @OneToMany(mappedBy="customer")
    private Set<Order> orders;

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

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
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

    public Set<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return age == customer.age && id.equals(customer.id) && first_name.equals(customer.first_name) && second_name.equals(customer.second_name) && email.equals(customer.email) && gender.equals(customer.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, second_name, email, gender, age);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
