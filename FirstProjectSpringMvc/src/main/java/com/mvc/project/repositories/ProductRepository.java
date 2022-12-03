package com.mvc.project.repositories;

import com.mvc.project.dto.ProductDTO;
import com.mvc.project.mapper.ProductMapper;
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

    public List<ProductDTO> getProductList(){
        final String queryStatement = "SELECT * FROM product";
        Object[] params = new Object[] {};
        List<ProductDTO> products = this.getJdbcTemplate()
                .query(queryStatement, params, new ProductMapper());

        return products;
    }
}
