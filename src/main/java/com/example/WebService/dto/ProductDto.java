package com.example.WebService.dto;

public record ProductDto(Long id, String nom, double prix, String description, String picture, Long categorieId) {
}
