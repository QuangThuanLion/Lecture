package com.mvc.project.repositories;

import com.mvc.project.dto.CategoryDTO;
import com.mvc.project.mapper.CategoryMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryRepository extends JdbcDaoSupport {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CategoryRepository(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.setDataSource(dataSource);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * @Desc get all category
     * @return
     */
    public List<CategoryDTO> getAllCategory() {
        final String queryStatement = "SELECT * FROM category";
        List<CategoryDTO> categories = this.getJdbcTemplate().query(
                queryStatement,
                new CategoryMapper());
        return categories;
    }

    /**
     * @Desc get all category other way 1
     * @return
     */
    public List<CategoryDTO> getAllCategoryWay1() {
        final String queryStatement = "SELECT * FROM category";
        List<CategoryDTO> categories = this.getJdbcTemplate().query(
                queryStatement,
                new BeanPropertyRowMapper<>(CategoryDTO.class));
        return categories;
    }

    /**
     * @Desc get all category other way 3
     * @return
     */
    public List<CategoryDTO> getAllCategoryWay2() {
        final String queryStatement = "SELECT * FROM category";
        List<CategoryDTO> categories = this.getJdbcTemplate().query(
                queryStatement,
                (rs, rowNumber) -> new CategoryDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
        return categories;
    }

    /**
     * @Desc create category
     * @param categoryDTO
     */
    public void createCategory(CategoryDTO categoryDTO) {
        final String queryStatement = "INSERT INTO category(name,description) VALUES (? ,?)";
        this.getJdbcTemplate().update(queryStatement, categoryDTO.getName(), categoryDTO.getDescription());
    }

    /**
     * @Desc find category by id
     * @param categoryId
     * @return
     */
    public CategoryDTO findCategoryById(Integer categoryId) {
        final String queryStatement = "SELECT * FROM category WHERE id = ?";
        CategoryDTO categoryDTO = this.getJdbcTemplate()
                .queryForObject(queryStatement, new CategoryMapper(), categoryId);
        return categoryDTO;
    }

    /**
     * @Desc update category
     * @param categoryDTO
     */
    public void updateCategory(CategoryDTO categoryDTO) {
        StringBuilder stringBuilder = new StringBuilder("UPDATE category ")
                .append("SET name = :name, description = :description ")
                .append("WHERE id = :id ");
        Map<String, Object> params = new HashMap<>();
        params.put("name", categoryDTO.getName());
        params.put("description", categoryDTO.getDescription());
        params.put("id",categoryDTO.getId());

        namedParameterJdbcTemplate.update(stringBuilder.toString(), params);
    }
}