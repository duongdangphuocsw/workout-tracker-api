package com.example.workout_tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.workout_tracker.dto.CreateCategoryDTO;
import com.example.workout_tracker.model.Category;
import com.example.workout_tracker.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Get all categories
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    // Get category by id
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Create category
    public Category createCategory(CreateCategoryDTO category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        return categoryRepository.save(newCategory);
    }

    // Update category
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }

    // Delete category
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
