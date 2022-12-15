package com.mvc.project.services;

import com.mvc.project.iservices.IProduct;
import com.mvc.project.dto.ProductDTO;
import com.mvc.project.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServices implements IProduct {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDTO productDTO) {
        productRepository.createProduct(productDTO);
    }

    @Override
    public List<ProductDTO> getProductList() {
        return productRepository.getProductList();
    }

    @Override
    public ProductDTO findByProductId(Integer productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public void updateProductById(ProductDTO productDTO) {
        productRepository.updateProductById(productDTO);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productRepository.deleteProductById(productId);
    }

    @Override
    public List<ProductDTO> searchByKeyword(String keyword) {
        return productRepository.searchByKeyword(keyword);
    }
}
