package com.example.WebService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebService.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
