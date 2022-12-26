package com.boot.project.iservice;

import com.boot.project.dto.ProductDTO;
import com.boot.project.entities.Product;
import com.boot.project.request.ProductRequest;
import java.util.List;
import java.util.UUID;

public interface IProduct {
    List<Product> getAllListProductLazyAndEagerEntityGraph();

    List<Product> getAllProducts();

    List<Product> getAllListProductLazyAndEagerTransaction();

    List<Object[]> getAllListProductLazyAndEager();

    Product createProduct(ProductRequest productRequest) throws Exception;

    Product updateProduct(UUID productId, ProductRequest productRequest) throws Exception;

    void deleteProductById(UUID productId) throws Exception;

    List<ProductDTO> getAllProductJoinCategoriesByStatus(boolean status);

    void updateProductByPriceAndStatus(Double price, Boolean status);
}
