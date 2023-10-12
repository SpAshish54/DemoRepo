package com.learnings.capstone.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "internal_name")
    private String internalName;

    @Column(name = "details")
    private String details;

    @Column(name = "max_products_per_location")
    private Integer maxProductsPerLocation;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Feature> features;
}
