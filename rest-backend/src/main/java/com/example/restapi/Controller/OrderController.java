package com.example.restapi.Controller;


import com.example.restapi.Domain.Order;
import com.example.restapi.Service.OrderService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Order>> all(){
        return this.service.all();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Order> one(@PathVariable Long id){
        return this.service.one(id);
    }

    @PostMapping({"","/"})
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Order> newOrder(@RequestBody Order order) {
        return this.service.newOrder(order);
    }

//    @PostMapping("/orders/{id}/products")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void order_products(@PathVariable Long id,@RequestBody)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Order> replaceOrder(@RequestBody Order new_order, @PathVariable Long id) {
        return this.service.replaceOrder(new_order,id);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable Long id) {
        this.service.deleteOrder(id);
    }

    @PutMapping("/{id}/customers")
    @ResponseStatus(HttpStatus.OK)
    public void setCustomer(@PathVariable Long id, @RequestBody Long customer_id) {
        this.service.setCustomer(id,customer_id);
    }

    @GetMapping("orders/filter/{price}")
    @ResponseStatus(HttpStatus.OK)
    public List<EntityModel<Order>> filter_total(@PathVariable Double price){
        return this.service.filter_total(price);
    }
}
