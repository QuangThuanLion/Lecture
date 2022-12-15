package com.mvc.project.iservices;

import com.mvc.project.dto.ProductDTO;
import java.util.List;

public interface IProduct {

    public void createProduct(ProductDTO productDTO);

    List<ProductDTO> getProductList();

    ProductDTO findByProductId(Integer productId);

    void updateProductById(ProductDTO productDTO);

    void deleteProductById(Integer productId);

    List<ProductDTO> searchByKeyword(String keyword);
}
