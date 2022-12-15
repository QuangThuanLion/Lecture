package com.boot.project.controllers;

import com.boot.project.entities.Category;
import com.boot.project.services.CategoryService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/categories")
    public String createCategory() {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setCategoryName("First Category");
        category.setCategoryDescription("First Category Description");

        String message = "create category successfully";
        try {
            categoryService.createCategory(category);
        } catch (Exception ex) {
            message = ex.getMessage();
        }

        return message;
    }
}
