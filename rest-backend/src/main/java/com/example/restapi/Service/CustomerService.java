package com.example.restapi.Service;

import com.example.restapi.Assembler.CustomerModelAssembler;
import com.example.restapi.Controller.CustomerController;
import com.example.restapi.Domain.Customer;
import com.example.restapi.Exceptions.CustomerNotFoundException;
import com.example.restapi.Repository.CustomerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    private final CustomerModelAssembler assembler;

    @Autowired
    public CustomerService(CustomerRepository repository, CustomerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public CollectionModel<EntityModel<Customer>> all(){
         List<EntityModel<Customer>> list = this.repository.findAll()
                .stream().map(this.assembler::toModel).toList();
        return CollectionModel.of(list,linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }

    public EntityModel<Customer> one(Long id){
        Customer customer =  this.repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

        return this.assembler.toModel(customer);
    }

    public EntityModel<Customer> newCustomer(Customer customer) {
        return this.assembler.toModel(this.repository.save(customer));
    }

    public EntityModel<Customer> replaceCustomer(Customer new_customer, Long id) {
        Customer updated_customer =  this.repository.findById(id).
                map(customer -> {
                    if (new_customer.getName() != null)
                        customer.setName(new_customer.getName());

                    if (new_customer.getEmail() != null)
                        customer.setEmail(new_customer.getEmail());

                    if (new_customer.getGender() != null)
                        customer.setGender(new_customer.getGender());

                    if (new_customer.getAge() >= 18)
                        customer.setAge(new_customer.getAge());

                    return this.repository.save(customer);
                }).orElseThrow(() -> new CustomerNotFoundException(id));
        return this.assembler.toModel(updated_customer);
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }
}
