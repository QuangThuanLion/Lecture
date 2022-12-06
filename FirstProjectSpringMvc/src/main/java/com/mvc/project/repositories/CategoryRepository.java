package com.mvc.project.repositories;

import com.mvc.project.dto.CategoryDTO;
import com.mvc.project.mapper.CategoryMapper;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryRepository extends JdbcDaoSupport {

    @Autowired
    public CategoryRepository(DataSource dataSource) {
        this.setDataSource(dataSource);
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
}