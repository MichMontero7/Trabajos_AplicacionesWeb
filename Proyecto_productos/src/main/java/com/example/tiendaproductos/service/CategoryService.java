package com.example.tiendaproductos.service;

import com.example.tiendaproductos.exception.ResourceNotFoundException;
import com.example.tiendaproductos.model.Category;
import com.example.tiendaproductos.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repoCategory;

    public CategoryService(CategoryRepository repoCategory){
        this.repoCategory = repoCategory;
    }

    public List<Category> getAll(){ return repoCategory.findAll(); }

    public Optional<Category> findById(Long id){
        return repoCategory.findById(id);
    }

    public Category save(Category category){
        /*Category categoryd = repoCategory.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not foun with id: " + id)
        );
        category.setName(category);*/
        return repoCategory.save(category);
    }

    public Category update(Long id, Category category){
        Category categoryBD = repoCategory.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with id: " + id)
        );

        categoryBD.setName(category.getName());
        return repoCategory.save(categoryBD);
    }

    public boolean delete(Long id){
        if (!repoCategory.existsById(id)){
            return false;
        }

        repoCategory.deleteById(id);
        return true;
    }
}
