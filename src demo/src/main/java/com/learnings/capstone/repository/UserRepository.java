package com.learnings.capstone.repository;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learnings.capstone.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
	
	public Optional<Users> findByName(String name);

	public boolean existsByName(String name);

}
