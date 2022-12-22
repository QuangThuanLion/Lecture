package com.boot.project.controllers;

import com.boot.project.dto.ProductDTO;
import com.boot.project.entities.Product;
import com.boot.project.iservice.IProduct;
import com.boot.project.request.ProductRequest;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {

    @Autowired
    private IProduct iProduct;

    @GetMapping(path = "/products")
    public List<Product> getAllProducts() {
        List<Product> products = iProduct.getAllProducts();
        return products;
    }

    @GetMapping(path = "/products/categories/{status}")
    public ResponseEntity<?> getProductInnerJoinCategories(
            @PathVariable(name = "status") boolean status)
    {
        try {
            List<ProductDTO> products = iProduct
                    .getAllProductJoinCategoriesByStatus(status);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * @param productRequest
     * @return
     * @throws Exception
     */
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/products",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(
            @RequestBody ProductRequest productRequest)
            throws Exception
    {
        try {
           return new ResponseEntity<>(iProduct.createProduct(productRequest), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * @param productId
     * @param productRequest
     * @return
     */
    @PutMapping(path = "/products/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(
            @PathVariable(name = "productId") String productId,
            @RequestBody ProductRequest productRequest)
    {
        try {
            UUID uuidProduct = UUID.fromString(productId);
            return iProduct.updateProduct(uuidProduct, productRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param productId
     * @return
     */
    @DeleteMapping(path = "/products/{productId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteProduct(
            @PathVariable(name = "productId") UUID productId)
    {
        try {
            iProduct.deleteProductById(productId);
            return "Delete product successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(path = "/products/update")
    public ResponseEntity<?> updateProductByPriceAndStatus(
            @RequestParam(name = "price") Double price,
            @RequestParam(name = "status") Boolean status)
    {
        iProduct.updateProductByPriceAndStatus(price, status);
        return new ResponseEntity<>("Update success", HttpStatus.CREATED);
    }
}
