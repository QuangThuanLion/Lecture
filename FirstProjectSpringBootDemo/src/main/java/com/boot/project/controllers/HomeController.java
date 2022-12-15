package com.boot.project.controllers;

import com.boot.project.entities.Category;
import com.boot.project.services.CategoryService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/home-page")
    public String homePage() {
        Category category = new Category();
        category.setCategoryName("first category");
        category.setCategoryDescription("first description");
        category.setId(UUID.randomUUID());
        categoryService.addCategory(category);
        return "Home Page";
    }

    @GetMapping(path = "/getAll")
    public List<Category> getAll() {
        List<Category> categories = categoryService.getAll();
        return categories;
    }
}
