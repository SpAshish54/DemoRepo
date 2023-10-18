package com.learnings.capstone.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnings.capstone.entity.Users;
import com.learnings.capstone.exception.CatalogBusinessException;
import com.learnings.capstone.repository.UserRepository;

@Service
public class UserService {

    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<Users> authenticate(String username, String password) {
        Optional<Users> optUser = userRepository.findByName(username);
        if (optUser.isEmpty()) {
            throw new CatalogBusinessException("User not found");
        }
        if (!optUser.get().getPassword().equals(password)) {
            return Optional.empty();
        }
        return optUser;
    }

    public Users create(Users user) {
        user.setPassword("{bcrypt}" + passwordEncoder.encode(user.getPassword()));
        //user.setPassword("{noop}" + user.getPassword());
        return userRepository.save(user);
    }

    public Optional<Users> getById(long id) {
        return userRepository.findById(id);
    }

    public Optional<Users> getByName(String name) {
        return userRepository.findByName(name);
    }

    
}
