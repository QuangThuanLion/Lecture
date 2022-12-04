package com.mvc.project.anotation.repository;

import com.mvc.project.anotation.dto.Product;
import com.mvc.project.anotation.mapper.ProductMapper;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductRepository extends JdbcDaoSupport {

    @Autowired
    public ProductRepository(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Product> getAllListProduct() {
        final String queryStatement = "SELECT * FROM product";
        Object[] params = new Object[] {};
        List<Product> result = this.getJdbcTemplate().query(queryStatement, params, new ProductMapper());
        return result;
    }
}
