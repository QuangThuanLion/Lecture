package com.mvc.project.anotation.mapper;

import com.mvc.project.anotation.dto.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getDouble("price"));
        product.setDescription(resultSet.getString("description"));
        product.setCreatedTime(resultSet.getTimestamp("created_time"));
        product.setStatus(resultSet.getBoolean("status"));
        product.setCategoryId(resultSet.getInt("category_id"));

        return  product;
    }
}
