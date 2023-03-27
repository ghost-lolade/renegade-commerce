package com.renegade.ecommerce.model;

import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_name")
    private @NotBlank String categoryName;

    private @NotBlank String description;

    private @NotBlank String imageUrl;

    public Category () {

    }
    public Category (@NotBlank String categoryName, @NotBlank String description) {
        this.categoryName = categoryName;
        this.description = description;

    }

    public Category (@NotBlank String categoryName, @NotBlank String description, @NotBlank String imageUrl) {
        this.categoryName = categoryName;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
