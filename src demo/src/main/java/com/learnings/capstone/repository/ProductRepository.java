package com.learnings.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnings.capstone.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getProductEntityByInternalName(String internalName);


}
