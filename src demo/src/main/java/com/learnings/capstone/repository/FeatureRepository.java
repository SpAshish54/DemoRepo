package com.learnings.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.learnings.capstone.entity.Feature;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

    List<Feature> findByProduct_InternalName(String productInternalName);

    Feature getFeatureEntityByInternalName(String internalName);

}
