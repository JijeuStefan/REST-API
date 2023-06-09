package com.example.restapi.Controller;

import com.example.restapi.Domain.Product;
import com.example.restapi.Service.ProductService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Product>> all(){
        return this.service.all();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Product> one(@PathVariable Long id){
        return this.service.one(id);
    }

    @PostMapping({"","/"})
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Product> newProduct(@RequestBody Product order) {
        return this.service.newProduct(order);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Product> replaceProduct(@RequestBody Product new_product, @PathVariable Long id) {
        return this.service.replaceProduct(new_product,id);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id) {
        this.service.deleteProduct(id);
    }

}

