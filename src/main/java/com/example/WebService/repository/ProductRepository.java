package com.example.WebService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebService.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
