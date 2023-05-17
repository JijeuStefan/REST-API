package com.example.restapi.Controller;

import java.util.List;

import com.example.restapi.Assembler.CustomerModelAssembler;
import com.example.restapi.Domain.Customer;
import com.example.restapi.Exceptions.CustomerNotFoundException;
import com.example.restapi.Repository.CustomerRepository;
import com.example.restapi.Service.CustomerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

}
