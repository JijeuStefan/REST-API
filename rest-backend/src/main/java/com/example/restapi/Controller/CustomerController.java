package com.example.restapi.Controller;

import com.example.restapi.Domain.Customer;
import com.example.restapi.Service.CustomerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Customer>> all(){
        return this.service.all();
    }

    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Customer> one(@PathVariable Long id){
        return this.service.one(id);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Customer> newCustomer(@RequestBody Customer customer) {
        return this.service.newCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Customer> replaceCustomer(@RequestBody Customer new_customer, @PathVariable Long id) {
        return this.service.replaceCustomer(new_customer,id);
    }

    @DeleteMapping ("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        this.service.deleteCustomer(id);
    }

    @PostMapping("/customers/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public void placeOrder(@PathVariable Long id, @RequestBody List<Long> orders) {
        this.service.placeOrder(id,orders);
    }

}
