package com.boot.project.controllers;

import com.boot.project.entities.Role;
import com.boot.project.iservice.IRole;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/v1")
public class RoleController {

    @Autowired
    private IRole roleService;

    @GetMapping(path = "/roles")
    public ResponseEntity<List<Role>> getAllRole(){
        List<Role> roles = roleService.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    /**
     * @param roleRequest
     * @return
     * @throws Exception
     */
    @PostMapping(path = "/roles",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createRole(
            @RequestBody Map<String, Object> roleRequest)
            throws Exception {
        try {
            Role savedRole = roleService.createRole(roleRequest);
            return new ResponseEntity<>(savedRole, HttpStatus.OK);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
