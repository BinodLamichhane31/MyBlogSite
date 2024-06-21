package com.example.myblogsite.controller;

import com.example.myblogsite.pojo.CategoryPojo;
import com.example.myblogsite.service.CategoryService;
import com.example.myblogsite.shared.pojo.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryPojo> addCategory(@Valid @RequestBody CategoryPojo categoryPojo) {
        CategoryPojo addedCategory = this.categoryService.createCategory(categoryPojo);
        return new ResponseEntity<>(addedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryPojo>> getAllCategories() {
        return new ResponseEntity<>(this.categoryService.getAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryPojo> getCategoryById(@PathVariable Long categoryId) {
        return new ResponseEntity<>(this.categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryPojo> updateCategory(@Valid @RequestBody CategoryPojo categoryPojo,@PathVariable Long categoryId) {
        CategoryPojo updatedCategory = this.categoryService.updateCategory(categoryPojo,categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully.",true), HttpStatus.OK);
    }
}
