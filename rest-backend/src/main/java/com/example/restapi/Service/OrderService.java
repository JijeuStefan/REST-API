package com.example.restapi.Service;


import com.example.restapi.Assembler.OrderModelAssembler;
import com.example.restapi.Controller.OrderController;
import com.example.restapi.Domain.Order;
import com.example.restapi.Exceptions.OrderNotFoundException;
import com.example.restapi.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OrderService {
    private final OrderRepository repository;

    private final OrderModelAssembler assembler;

    @Autowired
    public OrderService(OrderRepository repository, OrderModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public CollectionModel<EntityModel<Order>> all(){
        List<EntityModel<Order>> orders = this.repository.findAll()
                .stream().map(this.assembler::toModel).toList();
        return CollectionModel.of(orders,linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    public EntityModel<Order> one(Long id){
        Order order =  this.repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return this.assembler.toModel(order);
    }

    public EntityModel<Order> newOrder(Order order) {
        return this.assembler.toModel(this.repository.save(order));
    }

    public EntityModel<Order> replaceOrder(Order new_order, Long id) {
        Order updated_order =  this.repository.findById(id).
                map(order -> {
                    if (new_order.getOrder_date() != null)
                        order.setOrder_date(new_order.getOrder_date());

                    if (new_order.getOrder_total() != null)
                        order.setOrder_total(new_order.getOrder_total());

                    if (new_order.getOrder_status() != null)
                        order.setOrder_status(new_order.getOrder_status());

                    return this.repository.save(order);
                }).orElseThrow(() -> new OrderNotFoundException(id));
        return this.assembler.toModel(updated_order);
    }

    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}
