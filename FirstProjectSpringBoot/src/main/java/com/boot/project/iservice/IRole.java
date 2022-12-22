package com.boot.project.iservice;

import com.boot.project.entities.Role;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface IRole {
    Role createRole(Map<String, Object> roleRequest) throws Exception;

    List<Role>  getAll();
}
