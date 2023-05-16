package com.example.restapi.Controller;


import com.example.restapi.Assembler.OrderModelAssembler;
import com.example.restapi.Domain.Orders;
import com.example.restapi.Exceptions.OrderNotFoundException;
import com.example.restapi.Repository.OrderRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrderController {
    private final OrderRepository repository;

    private final OrderModelAssembler assembler;

    public OrderController(OrderRepository repository, OrderModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/orders")
    public CollectionModel<EntityModel<Orders>> all(){
        List<EntityModel<Orders>> orders = this.repository.findAll()
                .stream().map(this.assembler::toModel).toList();

        return CollectionModel.of(orders,linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/orders/{id}")
    public EntityModel<Orders> one(@PathVariable Long id){
        Orders order =  this.repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        return this.assembler.toModel(order);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> newOrder(@RequestBody Orders order) {
        EntityModel<Orders> entityModel = this.assembler.toModel(this.repository.save(order));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> replaceOrder(@RequestBody Orders new_order, @PathVariable Long id) {
        Orders updated_order =  this.repository.findById(id).
                map(order -> {
                    order.setOrder_date(new_order.getOrder_date());
                    order.setOrder_total(new_order.getOrder_total());
                    order.setOrder_status(new_order.getOrder_status());
                    return this.repository.save(order);
                }).orElseGet(()->{new_order.setId(id);
                    return this.repository.save(new_order);
                });
        EntityModel<Orders> entityModel =  this.assembler.toModel(updated_order);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping ("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
