package com.boot.project.services;

import com.boot.project.entities.Category;
import com.boot.project.repositories.CategoryRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    EntityManager entityManager;

    @Autowired
    CategoryRepository categoryRepository;

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
