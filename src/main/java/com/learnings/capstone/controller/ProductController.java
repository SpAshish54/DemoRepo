package com.learnings.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnings.capstone.dto.FeatureDTO;
import com.learnings.capstone.dto.ProductDTO;
import com.learnings.capstone.entity.Product;
import com.learnings.capstone.entity.Users;
import com.learnings.capstone.repository.UserRepository;
import com.learnings.capstone.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private ProductService productService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

    

    @GetMapping("/health")
    public String checkhealth() {
        return "healthy";
    }
	
    @PostMapping("/products")
    public ResponseEntity<Void> addProduct(@RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        ProductDTO productDTO = productService.getProductById(productId);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOs = productService.getAllProducts();
        return ResponseEntity.ok(productDTOs);
    }
    
    @GetMapping("/products/{productId}/features")
    public ResponseEntity<List<FeatureDTO>> getFeaturesByProductId(@PathVariable Long productId) {
        List<FeatureDTO> featureDTOs = productService.getFeaturesByProductId(productId);
        return ResponseEntity.ok(featureDTOs);
    }
    
    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(productId, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

	@PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user) {

        try {

            if (userRepository.existsByName(user.getName())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
            }
            
            user.setName(user.getName());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(user.getRole());
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }

    }
    
    private boolean userMatchesPassword(Users user, String password) {
        return user.getPassword().equals(password);
    }
}
