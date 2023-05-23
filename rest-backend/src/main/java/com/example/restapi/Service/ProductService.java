package com.example.restapi.Service;

import com.example.restapi.Assembler.ProductModelAssembler;
import com.example.restapi.Controller.CustomerController;
import com.example.restapi.Controller.ProductController;
import com.example.restapi.Domain.Product;
import com.example.restapi.Exceptions.ProductNotFoundException;
import com.example.restapi.Repository.OrderRepository;
import com.example.restapi.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Service
public class ProductService {
    private final ProductRepository repository;

    private final OrderRepository orderRepository;

    private final ProductModelAssembler assembler;

    @Autowired
    public ProductService(ProductRepository repository, OrderRepository orderRepository, ProductModelAssembler assembler) {
        this.repository = repository;
        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    public CollectionModel<EntityModel<Product>> all() {
        List<EntityModel<Product>> list = this.repository.findAll()
                .stream().map(this.assembler::toModel).toList();
        return CollectionModel.of(list, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    public EntityModel<Product> one(Long id) {
        Product product = this.repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return this.assembler.toModel(product);
    }

    public EntityModel<Product> newProduct(Product product) {
        return this.assembler.toModel(this.repository.save(product));
    }

    public EntityModel<Product> replaceProduct(Product new_product, Long id) {
        Product updated_product = this.repository.findById(id).
                map(product -> {
                    if (new_product.getName() != null)
                        product.setName(new_product.getName());

                    if (new_product.getCategory() != null)
                        product.setCategory(new_product.getCategory());

                    if (new_product.getReviews() > 0 && new_product.getReviews() <= 5)
                        product.setReviews(new_product.getReviews());

                    return this.repository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
        return this.assembler.toModel(updated_product);
    }

    public void deleteProduct(Long id) {
        if (this.repository.findById(id).isEmpty())
            throw new ProductNotFoundException(id);
        repository.deleteById(id);
    }
}
