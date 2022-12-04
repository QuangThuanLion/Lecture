package com.mvc.project.repositories;

import com.mvc.project.dto.CategoryDTO;
import com.mvc.project.mapper.CategoryMapper;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<CategoryDTO> getAllCategory() {
        final String queryStatement = "SELECT * FROM category";
        Object[] params = new Object[] {};
        List<CategoryDTO> categories = this.getJdbcTemplate().query(queryStatement, params, new CategoryMapper());
        return categories;
    }
}
