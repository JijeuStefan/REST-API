package com.example.restapi.Controller;


import com.example.restapi.Domain.Order;
import com.example.restapi.Service.OrderService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Order>> all(){
        return this.service.all();
    }

    @GetMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Order> one(@PathVariable Long id){
        return this.service.one(id);
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Order> newOrder(@RequestBody Order order) {
        return this.service.newOrder(order);
    }

    @PutMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Order> replaceOrder(@RequestBody Order new_order, @PathVariable Long id) {
        return this.service.replaceOrder(new_order,id);
    }

    @DeleteMapping ("/orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        this.service.deleteOrder(id);
    }
}
