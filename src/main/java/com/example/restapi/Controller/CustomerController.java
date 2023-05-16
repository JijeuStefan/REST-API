package com.example.restapi.Controller;

import java.util.List;

import com.example.restapi.Assembler.CustomerModelAssembler;
import com.example.restapi.Domain.Customer;
import com.example.restapi.Exceptions.CustomerNotFoundException;
import com.example.restapi.Repository.CustomerRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CustomerController {
    private final CustomerRepository repository;

    private final CustomerModelAssembler assembler;

    public CustomerController(CustomerRepository repository, CustomerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/customers")
    public CollectionModel<EntityModel<Customer>> all(){
        List<EntityModel<Customer>> customers = this.repository.findAll()
                .stream().map(this.assembler::toModel).toList();

        return CollectionModel.of(customers,linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }

    @GetMapping("/customers/{id}")
    public EntityModel<Customer> one(@PathVariable Long id){
        Customer customer =  this.repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

        return this.assembler.toModel(customer);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> newCustomer(@RequestBody Customer customer) {
        EntityModel<Customer> entityModel = this.assembler.toModel(this.repository.save(customer));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> replaceCustomer(@RequestBody Customer new_customer, @PathVariable Long id) {
        Customer updated_customer =  this.repository.findById(id).
                map(customer -> {
                    customer.setName(new_customer.getName());
                    customer.setEmail(new_customer.getEmail());
                    customer.setGender(new_customer.getGender());
                    customer.setAge(new_customer.getAge());
                    return this.repository.save(customer);
                }).orElseGet(()->{new_customer.setId(id);
                    return this.repository.save(new_customer);
                });
        EntityModel<Customer> entityModel =  this.assembler.toModel(updated_customer);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping ("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
