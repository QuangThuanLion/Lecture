package com.boot.project.controllers;

import com.boot.project.entities.Product;
import com.boot.project.request.ProductRequest;
import com.boot.project.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/products")
    public List<Product> getAllProducts() {
        List<Product> products = productService
                .getAllProducts();
        return products;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/products",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product createProduct(@RequestBody ProductRequest product)
            throws Exception
    {
        Product savedProduct;
        try {
            savedProduct = productService.createProduct(product);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return savedProduct;
    }
}
