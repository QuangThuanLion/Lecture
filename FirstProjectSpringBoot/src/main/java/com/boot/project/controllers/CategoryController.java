package com.boot.project.controllers;

import com.boot.project.entities.Category;
import com.boot.project.services.CategoryService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping(path = "/categories")
    public List<Category> getALlCategories() {
        List<Category> categories = categoryService.findAll();
        return categories;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PutMapping(path = "/categories/{categoryId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Category updateCategories(@PathVariable(name = "categoryId") UUID categoryId,
                                     @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(category, categoryId);
        return updatedCategory;
    }
}
