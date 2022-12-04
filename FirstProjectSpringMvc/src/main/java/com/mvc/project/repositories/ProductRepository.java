package com.mvc.project.repositories;

import com.mvc.project.dto.ProductDTO;
import com.mvc.project.mapper.ProductMapper;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductRepository extends JdbcDaoSupport {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public ProductRepository(DataSource dataSource,
                             NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.setDataSource(dataSource);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<ProductDTO> getProductList(){
        final String queryStatement = "SELECT * FROM product";
        List<ProductDTO> products = this.getJdbcTemplate()
                .query(queryStatement, new ProductMapper());

        return products;
    }

    public ProductDTO findByProductId(Integer productId) {
        //final String queryStatement = "SELECT * FROM product WHERE id = " + productId;
        //final String queryStatement = MessageFormat.format("SELECT * FROM product WHERE id = ${0}", productId);
        final String queryStatement = String.format("SELECT * FROM product WHERE id = %d", productId );
        ProductDTO productDTO = this.getJdbcTemplate().queryForObject(queryStatement, new ProductMapper());
        return productDTO;
    }

    public void createProduct(ProductDTO productDTO) {
        final String queryStatement =  "INSERT INTO product(name, price, description, created_time, status, category_id)" +
                "VALUES (:name, :price, :description, :createdTime, :status, :categoryId)";

//        final String queryStatement =  "INSERT INTO product(name, price, description, created_time, status, category_id)" +
//                "VALUES (?, ?, ?, ?, ?, ?)";
//        final StringBuilder queryStatement = new StringBuilder("INSERT INTO product(name, price, description, created_time, status, category_id) VALUES");
//        queryStatement.append("(")
//                .append("'").append(productDTO.getName())
//                .append("'").append(",")
//                .append(productDTO.getPrice())
//                .append(",").append("'")
//                .append(productDTO.getDescription())
//                .append("'").append(",").append("'")
//                .append(productDTO.getCreatedTime())
//                .append("'").append(",")
//                .append(productDTO.getStatus())
//                .append(",").append(productDTO.getCategoryId())
//                .append(")");
//        this.getJdbcTemplate().update(queryStatement.toString());
//        Object[] params = new Object[] {};
//        params[1] = productDTO.getName();
//        params[2] = productDTO.getPrice();
//        params[3] = productDTO.getDescription();
//        params[4] = productDTO.getCreatedTime();
//        params[5] = productDTO.getStatus();
//        params[6] = productDTO.getCategoryId();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", productDTO.getName())
                .addValue("price", productDTO.getPrice())
                .addValue("description", productDTO.getDescription())
                .addValue("createdTime", productDTO.getCreatedTime())
                .addValue("status", productDTO.getStatus())
                .addValue("categoryId", productDTO.getCategoryId());

        namedParameterJdbcTemplate.update(queryStatement, params);
//        this.getJdbcTemplate().update(queryStatement.toString());
    }
}
