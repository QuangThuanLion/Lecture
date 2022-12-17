package com.boot.project.controllers;

import com.boot.project.entities.Category;
import com.boot.project.entities.Product;
import com.boot.project.services.CategoryService;
import com.boot.project.services.ProductService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/products")
    public Product createProduct() {
        Product product = new Product();
        product.setProductName("First Product Name");
        product.setId(UUID.randomUUID());
        product.setProductDescription("First Product Description");

        UUID categoryId = UUID.fromString("5f8cb394-30ef-450e-a001-011d5958a3f3");
        Category category = categoryService.findById(categoryId);
        product.setCategory(category);
        Product savedProduct = productService.createProduct(product);

        return savedProduct;
    }
}
