package com.example.WebService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(nullable = false, name = "price", unique = false)
    private double price;

    @Column(name = "description", nullable = false, unique = false)
    @Lob
    private String description;

    @Column(name = "picture", nullable = false, unique = false)
    private String picture;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param nom the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param prix the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param image the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param categorie the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Produit [id=" + id + ", nom=" + name + ", prix=" + price + ", description=" + description + ", image="
                + picture + ", categorie=" + category + "]";
    }

}
