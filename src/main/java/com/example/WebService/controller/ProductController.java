package com.example.WebService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebService.dto.ProductDto;
import com.example.WebService.model.Product;
import com.example.WebService.service.ProductService;

@RestController
@RequestMapping("/categories/product")
public class ProductController {

    // Injection de dépendance
    @Autowired
    private ProductService productService;

    /**
     * Récupère un produit en fonction de l'id.
     *
     * @param id L'ID du produit à récupérer.
     * @return Le produit correspondant à l'ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Récupère la liste des produits.
     *
     * @return La liste de produits.
     */
    @GetMapping(value = "/productList")
    public List<Product> getProductList() {
        return productService.getProductList();
    }

    /**
     * Crée un nouveau produit.
     *
     * @param productDto Les données du produit à créer.
     * @return Le produit créée avec le statut HTTP 201 (Created).
     */
    @PostMapping(value = "/create/{categoryId}")
    public ResponseEntity<Product> createProductInCategory(@RequestBody ProductDto productDto,
            @PathVariable Long categoryId) {
        if (productDto == null) {
            return ResponseEntity.badRequest().build();
        }

        Product createdProduct = productService.createProduct(productDto, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Modifie un produit existante en fonction de l'ID.
     *
     * @param id         L'ID du produit à mettre à jour.
     * @param productDto Les nouvelles données du produit.
     * @return Le produit mis à jour avec le statut HTTP 200 (OK).
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        if (productDto == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Product> updatedProductOptional = productService.updateProduct(productDto);

        if (updatedProductOptional.isPresent()) {
            return ResponseEntity.ok(updatedProductOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Supprime un produit en fonction de l'ID.
     *
     * @param id L'ID du produit à supprimer.
     * @return Réponse avec le statut HTTP 204 (No Content) après la suppression.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
