package com.boot.project.services;

import com.boot.project.dto.ProductDTO;
import com.boot.project.entities.Category;
import com.boot.project.entities.Product;
import com.boot.project.iservice.IProduct;
import com.boot.project.repositories.CategoryRepository;
import com.boot.project.repositories.ProductRepository;
import com.boot.project.request.ProductRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class ProductService implements IProduct {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * @Desc Create Product Function
     * @param productRequest
     * @return
     */
    @Override
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

    /**
     * @param productId
     * @param productRequest
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product updateProduct(UUID productId, ProductRequest productRequest)
            throws Exception
    {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new Exception("Cannot find any product !");
        }

        UUID categoryId = productRequest.getCategoryId();
        Optional<Category> optionalCategory = categoryRepository
                .findById(categoryId);
        if (optionalCategory.isEmpty())
        {
            throw new Exception("Cannot find any category !");
        }

        Product productInDB = optionalProduct.get();
        productInDB.setProductName(productRequest.getName());
        productInDB.setPrice(productRequest.getPrice());
        productInDB.setStatus(productRequest.isStatus());
        productInDB.setCategory(optionalCategory.get());

        Product updatedProduct = productRepository.save(productInDB);
        return updatedProduct;
    }

    /**
     * @param productId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProductById(UUID productId) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new Exception("Cannot find any product !");
        }
        productRepository.deleteById(productId);
    }

    /**
     * @param status
     * @return
     */
    @Override
    public List<ProductDTO> getAllProductJoinCategoriesByStatus(boolean status)
    {
        List<Map<String, Object>> result = productRepository
                .getAllProductJoinCategoryByStatus(status);

        if (StringUtils.isEmpty(result)) {
            throw new RuntimeException("products is empty !");
        }
        List<ProductDTO> products = new ArrayList<>();
        for (Map<String, Object> x : result) {
            ProductDTO productDTO = new ProductDTO();

            byte[] id = (byte[]) x.getOrDefault("id", null);
            productDTO.setId(UUID.nameUUIDFromBytes(id));
            productDTO.setProductName(String.valueOf(x.getOrDefault("product_name",null)));
            productDTO.setPrice((Double) x.getOrDefault("price", null));
            productDTO.setStatus((Boolean) x.getOrDefault("status", false));
            productDTO.setCategoryName(String.valueOf(x.getOrDefault("category_name", null)));

            products.add(productDTO);
        }
        return products;
    }

    /**
     * @param price
     * @param status
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductByPriceAndStatus(Double price, Boolean status)
    {
        productRepository.updateProductByPriceAndStatus(price,status);
    }
}
