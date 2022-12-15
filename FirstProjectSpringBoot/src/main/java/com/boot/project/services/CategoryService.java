package com.boot.project.services;

import com.boot.project.entities.Category;
import com.boot.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createCategory(Category category) throws Exception {
        try {
            categoryRepository.save(category);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
