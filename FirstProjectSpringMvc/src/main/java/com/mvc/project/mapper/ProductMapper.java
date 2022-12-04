package com.mvc.project.mapper;

import com.mvc.project.dto.ProductDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProductMapper implements RowMapper<ProductDTO> {
    @Override
    public ProductDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(resultSet.getInt("id"));
        productDTO.setName(resultSet.getString("name"));
        productDTO.setPrice(resultSet.getDouble("price"));
        productDTO.setDescription(resultSet.getString("description"));
        productDTO.setStatus(resultSet.getBoolean("status"));
        productDTO.setCreatedTime(resultSet.getTimestamp("created_time"));
        productDTO.setCategoryId(resultSet.getInt("category_id"));

        return productDTO;
    }
}
