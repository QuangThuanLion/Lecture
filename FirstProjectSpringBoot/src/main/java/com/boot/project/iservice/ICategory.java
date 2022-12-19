package com.boot.project.iservice;

import com.boot.project.entities.Category;
import java.util.List;
import java.util.UUID;

public interface ICategory {

    void createCategory(Category category) throws Exception;

    List<Category> findAll();

    Category updateCategory(Category category, UUID categoryId);
}
