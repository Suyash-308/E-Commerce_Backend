package com.suyash.ecommerce_backend.service.serviceImp;

import com.suyash.ecommerce_backend.entity.Category;
import com.suyash.ecommerce_backend.repository.CategoryRepository;
import com.suyash.ecommerce_backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public Category updateCategory(Long id, Category category) {

        Category category1 = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category1.setName(category.getName());
        category1.setDescription(category.getDescription());

        return categoryRepository.save(category1);
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }

    @Override
    public void deleteCategory() {
        categoryRepository.deleteAll();
    }
}
