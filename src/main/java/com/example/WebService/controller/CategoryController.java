package com.example.WebService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.WebService.dto.CategoryDto;
import com.example.WebService.model.Category;
import com.example.WebService.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Récupère une catégorie en fonction de l'id.
     *
     * @param id L'ID de la catégorie à récupérer.
     * @return La catégorie correspondant à l'ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Récupère la liste des catégories.
     *
     * @return La liste de catégories.
     */
    @GetMapping(value = "/categoryList")
    public List<Category> getCategoryList() {
        return categoryService.getCategoryList();
    }

    /**
     * Crée une nouvelle catégorie.
     *
     * @param categoryDto Les données de la catégorie à créer.
     * @return La catégorie créée avec le statut HTTP 201 (Created).
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) {
        if (categoryDto == null) {
            return ResponseEntity.badRequest().build();
        }

        Category createdCategory = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    /**
     * Modifie une catégorie existante en fonction de l'ID.
     *
     * @param id          L'ID de la catégorie à mettre à jour.
     * @param categoryDto Les nouvelles données de la catégorie.
     * @return La catégorie mise à jour avec le statut HTTP 200 (OK).
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        if (categoryDto == null) {
            return ResponseEntity.badRequest().build();
        }

        Category updatedCategory = categoryService.updateCategory(categoryDto);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Supprime une catégorie en fonction de l'ID.
     *
     * @param id L'ID de la catégorie à supprimer.
     * @return Réponse avec le statut HTTP 204 (No Content) après la suppression.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
