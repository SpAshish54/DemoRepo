package com.learnings.capstone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnings.capstone.entity.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter, Long>{

    Parameter getParameterEntityByInternalName(String featureInternalName);

    List<Parameter> findByFeature_InternalName(String featureInternalName);

}
