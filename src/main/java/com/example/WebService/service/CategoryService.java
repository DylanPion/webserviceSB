package com.example.WebService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebService.dto.CategoryDto;
import com.example.WebService.model.Category;
import com.example.WebService.repository.CategoryRepository;

@Service
public class CategoryService {

    // Injection de dépendance
    @Autowired
    private CategoryRepository categoryRepository;

    /* Récupère La catégorie par Id */
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Récupère la liste des catégories
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    // Création d'une catégorie
    public Category createCategory(CategoryDto categoryDto) {
        /* Les informations du DTO */
        Category category = new Category();
        category.setDescription(categoryDto.description());
        category.setName(categoryDto.nom());

        return categoryRepository.save(category);
    }

    // Modification d'une catégorie
    public Optional<Category> updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.id()).orElse(null);

        if (category != null) {
            category.setDescription(categoryDto.description());
            category.setName(categoryDto.nom());
            return Optional.of(categoryRepository.save(category));
        } else {
            return Optional.empty();
        }
    }

    /* Supprimer une categorie */
    public boolean deleteCategory(Long idCategory) {
        if (idCategory != null && categoryRepository.existsById(idCategory)) {
            categoryRepository.deleteById(idCategory);
            return true;
        } else {
            return false;
        }
    }
}
