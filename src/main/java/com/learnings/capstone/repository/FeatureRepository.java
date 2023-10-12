package com.learnings.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnings.capstone.entity.Feature;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

}
