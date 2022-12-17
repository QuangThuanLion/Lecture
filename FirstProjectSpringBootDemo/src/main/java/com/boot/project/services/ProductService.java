package com.boot.project.services;

import com.boot.project.entities.Product;
import com.boot.project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        Product productSaved = productRepository.save(product);
        return productSaved;
    }
}
