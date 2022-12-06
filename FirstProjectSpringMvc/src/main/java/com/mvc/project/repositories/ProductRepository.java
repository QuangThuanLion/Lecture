package com.mvc.project.repositories;

import com.mvc.project.dto.ProductDTO;
import com.mvc.project.mapper.ProductMapper;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductRepository extends JdbcDaoSupport {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * @Desc injected constructor
     * @param dataSource
     * @param namedParameterJdbcTemplate
     */
    @Autowired
    public ProductRepository(DataSource dataSource,
                             NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.setDataSource(dataSource);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * @Desc get all products
     * @return
     */
    public List<ProductDTO> getProductList() {
        final String queryStatement = "SELECT * FROM product";
        List<ProductDTO> products = this.getJdbcTemplate()
                .query(queryStatement, new ProductMapper());

        return products;
    }

    /**
     * @param productId
     * @return
     */
    public ProductDTO findByProductId(Integer productId) {
        //final String queryStatement = MessageFormat.format("SELECT * FROM product WHERE id = {0}", productId);
        final String queryStatement = String.format("SELECT * FROM product WHERE id = %d", productId);
        ProductDTO productDTO = this.getJdbcTemplate().queryForObject(queryStatement, new ProductMapper());

        return productDTO;
    }

    /**
     * @Desc find by product way 1
     * @param productId
     * @return
     */
    public ProductDTO findByProductIdWay1(Integer productId) {
        final String queryStatement = "SELECT * FROM product WHERE id = ?";
        ProductDTO productDTO = this.getJdbcTemplate()
                .queryForObject(queryStatement, new Object[]{productId}, new ProductMapper());

        return productDTO;
    }

    /**
     * @Desc find by product way 2
     * @param productId
     * @return
     */
    public ProductDTO findByProductIdWay2(Integer productId) {
        final String queryStatement = "SELECT * FROM product WHERE id = ?";
        ProductDTO productDTO = this.getJdbcTemplate().queryForObject(
                queryStatement,
                new Object[]{productId},
                new BeanPropertyRowMapper<>(ProductDTO.class));
        return productDTO;
    }

    /**
     * @Desc find by product way 3
     * @param productId
     * @return
     */
    public ProductDTO findByProductIdWay3(Integer productId) {
        final String queryStatement = "SELECT * FROM product WHERE id = ?";
        ProductDTO productDTO = this.getJdbcTemplate().queryForObject(
                queryStatement,
                new Object[]{productId},
                (rs, rowNum) -> new ProductDTO(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_time"),
                        rs.getInt("category_id")));

        return productDTO;
    }

    /**
     * @Desc Create product
     * @param productDTO
     */
    public void createProduct(ProductDTO productDTO) {
        final String queryStatement = "INSERT INTO product(name, price, description, " +
                "created_time, status, category_id)" +
                "VALUES (:name, :price, :description, :createdTime, :status, :categoryId)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", productDTO.getName())
                .addValue("price", productDTO.getPrice())
                .addValue("description", productDTO.getDescription())
                .addValue("createdTime", productDTO.getCreatedTime())
                .addValue("status", productDTO.getStatus())
                .addValue("categoryId", productDTO.getCategoryId());

        namedParameterJdbcTemplate.update(queryStatement, params);
    }

    /**
     * @Desc insert product with name parameter
     * @param productDTOS
     * @return
     */
    public int[] batchInsertProductWithNamedParameter(List<ProductDTO> productDTOS) {
        final String queryStatement = "INSERT INTO product(name, price, " +
                "description, created_time, status, category_id)" +
                "VALUES (:name, :price, :description, :createdTime, :status, :categoryId)";
        List<SqlParameterSource> params = new ArrayList<>();
        productDTOS.forEach(x -> params.add(new BeanPropertySqlParameterSource(x)));

        int[] countInsert = namedParameterJdbcTemplate.batchUpdate(
                queryStatement,
                params.toArray(new SqlParameterSource[0]));
        return countInsert;
    }

    /**
     * @Desc insert product with name parameter way 2
     * @param productDTOS
     */
    public void batchInsertProductWithNamedParameterWay1(List<ProductDTO> productDTOS) {
        final String queryStatement = "INSERT INTO product(name, price, " +
                "description, created_time, status, category_id)" +
                "VALUES (:name, :price, :description, :createdTime, :status, :categoryId)";
        SqlParameterSource[] sqlParameterSources = SqlParameterSourceUtils.createBatch(productDTOS.toArray());
        namedParameterJdbcTemplate.batchUpdate(
                queryStatement,
                sqlParameterSources
        );
    }

    /**
     * @Desc insert product with name parameter way 4
     * @param productDTO
     */
    public void createProductWay4(ProductDTO productDTO) {
        final String queryStatement = "INSERT INTO product(name, price, description, created_time, status, category_id)" +
                "VALUES (:name, :price, :description, :createdTime, :status, :categoryId)";

        Map<String, Object> params = new HashMap<>();
        params.put("name", productDTO.getName());
        params.put("price", productDTO.getPrice());
        params.put("description", productDTO.getDescription());
        params.put("createdTime", productDTO.getCreatedTime());
        params.put("status", productDTO.getStatus());
        params.put("categoryId", productDTO.getCategoryId());

        namedParameterJdbcTemplate.update(queryStatement, params);
    }

    /**
     * @Desc insert product with name parameter way 3
     * @param productDTO
     */
    public void createProductWay3(ProductDTO productDTO) {
        final String queryStatement = "INSERT INTO " +
                "product(name, price, description, created_time, status, category_id)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(
                queryStatement,
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                productDTO.getCreatedTime(),
                productDTO.getStatus(),
                productDTO.getCategoryId());
    }

    /**
     * @Desc insert product with name parameter way 2
     * @param productDTO
     */
    public void createProductWay2(ProductDTO productDTO) {
        final StringBuilder queryStatement = new StringBuilder("INSERT INTO product(name, price, description, created_time, status, category_id) VALUES");
        queryStatement.append("(")
                .append("'").append(productDTO.getName())
                .append("'").append(",")
                .append(productDTO.getPrice())
                .append(",").append("'")
                .append(productDTO.getDescription())
                .append("'").append(",").append("'")
                .append(productDTO.getCreatedTime())
                .append("'").append(",")
                .append(productDTO.getStatus())
                .append(",").append(productDTO.getCategoryId())
                .append(")");
        this.getJdbcTemplate().update(queryStatement.toString());
    }

    /**
     * @Desc insert multiple product with name parameter
     * @param productDTOS
     * @return
     */
    public int[] batchInsertProducts(List<ProductDTO> productDTOS){
            final String queryStatement = "INSERT INTO product(name, price, description, " +
                    "created_time, status, category_id) VALUES (" +
                    "?, ?, ?, ?, ?, ?)";
            int[] countInsert = this.getJdbcTemplate().batchUpdate(
                    queryStatement,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, productDTOS.get(i).getName());
                            ps.setDouble(2, productDTOS.get(i).getPrice());
                            ps.setString(3, productDTOS.get(i).getDescription());
                            ps.setTimestamp(4, productDTOS.get(i).getCreatedTime());
                            ps.setBoolean(5, productDTOS.get(i).getStatus());
                            ps.setInt(6, productDTOS.get(i).getCategoryId());
                        }
                        @Override
                        public int getBatchSize() {
                            //insert bao nhieu fields
                            return productDTOS.size();
                        }
                    });
            return countInsert;
    }

    /**
     * @Desc insert multiple product with name parameter split batch size
     * @param productDTOS
     * @param batchSize
     * @return
     */
    public int [][] batchInsertSplitFile(List<ProductDTO> productDTOS, int batchSize) {
        //batchSize hiểu ở đây là mỗi lần insert thì insert bao nhieu item vào chứ không phải là
        //số lượng items insert vào
        final String queryStatement = "INSERT INTO product(name, price, description, " +
                "created_time, status, category_id) VALUES (" +
                "?, ?, ?, ?, ?, ?)";
        int[][] ints = this.getJdbcTemplate().batchUpdate(
                queryStatement,
                productDTOS,
                batchSize,
                (ps, productDTO) -> {
                    ps.setString(1, productDTO.getName());
                    ps.setDouble(2, productDTO.getPrice());
                    ps.setString(3, productDTO.getDescription());
                    ps.setTimestamp(4, productDTO.getCreatedTime());
                    ps.setBoolean(5, productDTO.getStatus());
                    ps.setInt(6, productDTO.getCategoryId());
                });
        return ints;
    }

    /**
     * @Desc update product with nameParameter
     * @param productDTO
     */
    public void updateProductById(ProductDTO productDTO) {
        final String queryStatement = "UPDATE product SET name = :name, price = :price, " +
                "description = :description, " +
                "created_time = :createdTime, " +
                "status = :status, " +
                "category_id = :categoryId " +
                "WHERE id = :id ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", productDTO.getName())
                .addValue("price", productDTO.getPrice())
                .addValue("description", productDTO.getDescription())
                .addValue("createdTime", productDTO.getCreatedTime())
                .addValue("status", productDTO.getStatus())
                .addValue("categoryId", productDTO.getCategoryId())
                .addValue("id", productDTO.getId());
        namedParameterJdbcTemplate.update(queryStatement, params);
    }

    /**
     * @Desc delete product by id
     * @param productId
     */
    public void deleteProductById(Integer productId) {
        final String queryStatement = "DELETE FROM product where id = ?";
        this.getJdbcTemplate().update(queryStatement, productId);
    }

    /**
     * @Desc Search by keyword
     * @param keyword
     * @return
     */
    public List<ProductDTO> searchByKeyword(String keyword) {
        final String queryStatement = "SELECT * FROM product WHERE name" +
                " LIKE :name OR description LIKE :description";

        Map<String, Object> params = new HashMap<>();
        params.put("name", "%"+keyword+"%");
        params.put("description", "%"+keyword+"%");

        List<ProductDTO> products = namedParameterJdbcTemplate.query(queryStatement, params, new ProductMapper());
        return products;
    }
}