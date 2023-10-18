package com.learnings.capstone.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.learnings.capstone.dto.FeatureDTO;
import com.learnings.capstone.dto.ProductDTO;
import com.learnings.capstone.entity.Users;
import com.learnings.capstone.repository.UserRepository;
import com.learnings.capstone.service.ProductService;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductService productService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCheckHealth() {
        // Arrange
        // No specific arrangement needed for this method

        // Act
        String response = productController.checkhealth();

        // Assert
        assertEquals("healthy", response);
    }

    @Test
    public void testAddProduct() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        doNothing().when(productService).addProduct(productDTO);

        // Act
        ResponseEntity<Void> response = productController.addProduct(productDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productService, times(1)).addProduct(productDTO);
    }

    @Test
    public void testGetProductByInternalName() {
        // Arrange
        String internalName = "sampleInternalName";
        ProductDTO expectedProductDTO = new ProductDTO();
        when(productService.getProductByInternalName(internalName)).thenReturn(expectedProductDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.getProductByInternalName(internalName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProductDTO, response.getBody());
        verify(productService, times(1)).getProductByInternalName(internalName);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<ProductDTO> expectedProductDTOs = Arrays.asList(new ProductDTO(), new ProductDTO());
        when(productService.getAllProducts()).thenReturn(expectedProductDTOs);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProductDTOs, response.getBody());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetFeaturesByProductInternalName() {
        // Arrange
        String internalName = "sampleInternalName";
        List<FeatureDTO> expectedFeatureDTOs = Arrays.asList(new FeatureDTO(), new FeatureDTO());
        when(productService.getFeaturesByProductInternalName(internalName)).thenReturn(expectedFeatureDTOs);

        // Act
        ResponseEntity<List<FeatureDTO>> response = productController.getFeaturesByProductInternalName(internalName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFeatureDTOs, response.getBody());
        verify(productService, times(1)).getFeaturesByProductInternalName(internalName);
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        when(productService.updateProduct(productDTO)).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.updateProduct(productDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
        verify(productService, times(1)).updateProduct(productDTO);
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        String internalName = "sampleInternalName";
        doNothing().when(productService).deleteProduct(internalName);

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(internalName);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(internalName);
    }

    public void testRegisterUser() {
        // Arrange
        Users user = new Users();
        when(userRepository.existsByName(user.getName())).thenReturn(false);
        doNothing().when(userRepository).save(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Act
        ResponseEntity<String> response = productController.registerUser(user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
        verify(userRepository, times(1)).existsByName(user.getName());
        verify(userRepository, times(1)).save(user);
        verify(passwordEncoder, times(1)).encode(user.getPassword());
    }
}


