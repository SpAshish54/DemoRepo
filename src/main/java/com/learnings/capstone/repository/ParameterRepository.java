package com.learnings.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnings.capstone.entity.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter, Long>{

}
