package com.mvc.project.mapper;

import com.mvc.project.dto.CategoryDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
public class CategoryMapper implements RowMapper<CategoryDTO> {
    @Override
    public CategoryDTO mapRow(ResultSet resultSet, int i) throws SQLException {

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .build();

        return categoryDTO;
    }
}