package com.boot.project.services;

import com.boot.project.entities.Category;
import com.boot.project.entities.Product;
import com.boot.project.repositories.CategoryRepository;
import com.boot.project.repositories.ProductRepository;
import com.boot.project.request.ProductRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * @Desc Create Product Function
     * @param productRequest
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Product createProduct(ProductRequest productRequest) throws Exception {
        UUID categoryId = productRequest.getCategoryId();
        Optional<Category> optional = categoryRepository
                .findById(categoryId);

        Category category;
        if (optional.isPresent())
        {
            category = optional.get();
        } else {
            throw new Exception("Cannot find any category !");
        }

        Product productEntity = new Product();
        productEntity.setId(UUID.randomUUID());
        productEntity.setProductName(productRequest.getName());
        productEntity.setStatus(productRequest.isStatus());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setCategory(category);

        Product savedProduct = productRepository.save(productEntity);
        return savedProduct;
    }
}
