package com.example.myblogsite.service;

import com.example.myblogsite.pojo.CategoryPojo;

import java.util.List;

public interface CategoryService {
    List<CategoryPojo> getAllCategory();
    CategoryPojo getCategoryById(Long categoryId);
    CategoryPojo createCategory(CategoryPojo categoryPojo);
    CategoryPojo updateCategory(CategoryPojo categoryPojo, Long categoryId);
    void deleteCategory(Long categoryId);
}
