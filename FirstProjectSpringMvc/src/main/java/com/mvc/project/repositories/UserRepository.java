package com.mvc.project.repositories;

import com.mvc.project.dto.UserDTO;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository
public class UserRepository extends JdbcDaoSupport {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserRepository(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * @Desc find username and paswword
     * @param username
     * @param password
     * @return
     */
    public UserDTO findByUsernameAndPassword(String username, String password) {
        final String queryStatement = "SELECT name, phone_number " +
                "FROM users " +
                "WHERE name = ? " +
                "AND phone_number = ?";

        UserDTO userDTO;
        try {
             userDTO = this.getJdbcTemplate().queryForObject(
                    queryStatement,
                    new Object[]{username, password},
                    (rs, rowNum) ->
                            new UserDTO(
                            rs.getString("name"),
                            rs.getString("phone_number")
                    )
            );
        } catch (Exception ex) {
            return null;
        }
        return userDTO;
    }
}
