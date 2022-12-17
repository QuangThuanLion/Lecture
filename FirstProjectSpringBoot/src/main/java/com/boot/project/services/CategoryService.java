package com.boot.project.services;

import com.boot.project.entities.Category;
import com.boot.project.repositories.CategoryRepository;
import java.util.List;
import java.util.UUID;
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
            } catch (Exception exception) {
                throw new Exception("ERROR WHEN SAVE CATEGORY");
            }
    }

    public List<Category> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Transactional(rollbackFor = Exception.class)
    public Category updateCategory(Category category, UUID categoryId) {
        category.setId(categoryId);
        Category updatedCategory = categoryRepository.save(category);
        return updatedCategory;
    }
}
