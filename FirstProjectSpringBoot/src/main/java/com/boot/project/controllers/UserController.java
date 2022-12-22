package com.boot.project.controllers;

import com.boot.project.entities.User;
import com.boot.project.iservice.IUser;
import com.boot.project.request.UserRequest;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private IUser userService;

    @PostMapping(path = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest) {
        try {
            User savedUser = userService.createUser(userRequest);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @GetMapping(path = "/users")
    public ResponseEntity getAlLUser(){
        List<User> users = userService.getAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }
}
