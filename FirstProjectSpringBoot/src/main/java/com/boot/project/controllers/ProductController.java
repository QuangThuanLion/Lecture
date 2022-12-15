package com.boot.project.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {

    @PostMapping(path = "/products")
    public String createProducts() {
        return "";
    }
}
