package com.example.restapi.Controller;

import com.example.restapi.Domain.Customer;
import com.example.restapi.Domain.Order;
import com.example.restapi.Service.CustomerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Customer>> all(){
        return this.service.all();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Customer> one(@PathVariable Long id){
        return this.service.one(id);
    }

    @PostMapping({"","/"})
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Customer> newCustomer(@RequestBody Customer customer) {
        return this.service.newCustomer(customer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Customer> replaceCustomer(@RequestBody Customer new_customer, @PathVariable Long id) {
        return this.service.replaceCustomer(new_customer,id);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        this.service.deleteCustomer(id);
    }

    @PostMapping("/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public void placeOrder(@PathVariable Long id, @RequestBody List<Order> orders) {
        this.service.placeOrder(id,orders);
    }

}
