package com.boot.project.services;

import com.boot.project.entities.Category;
import com.boot.project.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category findById(UUID categoryId) {
        Optional<Category> byId = categoryRepository.findById(categoryId);
        return byId.get();
    }
}
