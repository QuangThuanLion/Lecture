package com.boot.project.repositories;

import com.boot.project.entities.Product;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>
{
    /**
     * @param status
     * @return
     */
    @Query(value = "SELECT p.id, p.price, p.product_name, \n" +
            "p.status, c.category_name \n" +
            "FROM products p\n" +
            "INNER JOIN categories c\n" +
            "ON p.category_id = c.id\n" +
            "WHERE p.status = :status",
            nativeQuery = true)
    List<Map<String, Object>> getAllProductJoinCategoryByStatus(@Param(value = "status") boolean status);

    /**
     * @param price
     * @param status
     */
    @Query(
            value = "UPDATE products p SET p.status = :status WHERE p.price >= :price",
            nativeQuery = true)
    @Modifying
    void updateProductByPriceAndStatus(
            @Param(value = "price") Double price,
            @Param(value = "status") Boolean status);
}
