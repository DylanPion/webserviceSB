package com.example.WebService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebService.dto.ProductDto;
import com.example.WebService.model.Category;
import com.example.WebService.model.Product;
import com.example.WebService.repository.CategoryRepository;
import com.example.WebService.repository.ProductRepository;

@Service
public class ProductService {

    // Injection de dépendance
    @Autowired
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    /* Récupère Le produit par Id */
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Récupère la liste des produits
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    // Création d'un produit
    public Product createProduct(ProductDto productDto, Long categoryId) {
        // Recherche de la catégorie par ID
        Category category = categoryRepository.findById(categoryId).orElseThrow(null);

        /* Les informations du DTO */
        Product product = new Product();
        product.setDescription(productDto.description());
        product.setName(productDto.nom());
        product.setPicture(productDto.picture());
        product.setCategory(category);

        return productRepository.save(product);
    }

    // Modification d'un produit

    public Optional<Product> updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.id()).orElse(null);

        if (product != null) {
            product.setDescription(productDto.description());
            product.setName(productDto.nom());
            product.setPicture(productDto.picture());
            return Optional.of(productRepository.save(product));
        } else {
            return Optional.empty();
        }
    }

    /* Supprimer une categorie */
    public boolean deleteProduct(Long idProduct) {
        if (idProduct != null && productRepository.existsById(idProduct)) {
            productRepository.deleteById(idProduct);
            return true;
        } else {
            return false;
        }
    }
}
