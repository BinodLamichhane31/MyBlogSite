package com.example.myblogsite.service.impl;

import com.example.myblogsite.entity.Category;
import com.example.myblogsite.entity.User;
import com.example.myblogsite.exception.ResourceNotFoundException;
import com.example.myblogsite.pojo.CategoryPojo;
import com.example.myblogsite.pojo.UserPojo;
import com.example.myblogsite.repository.CategoryRepository;
import com.example.myblogsite.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<CategoryPojo> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryPojo> categories = categoryList.stream().map(category ->
                this.modelMapper.map(category, CategoryPojo.class)).collect(Collectors.toList());
        return categories;
    }

    @Override
    public CategoryPojo getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        return this.modelMapper.map(category, CategoryPojo.class);
    }

    @Override
    public CategoryPojo createCategory(CategoryPojo categoryPojo) {
        Category category = modelMapper.map(categoryPojo, Category.class);
        Category createdCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(createdCategory, CategoryPojo.class);
    }

    @Override
    public CategoryPojo updateCategory(CategoryPojo categoryPojo, Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));

        category.setCategoryTitle(categoryPojo.getCategoryTitle());
        category.setCategoryDescription(categoryPojo.getCategoryDescription());
        Category updatedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(updatedCategory, CategoryPojo.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepository.delete(category);
    }


}
