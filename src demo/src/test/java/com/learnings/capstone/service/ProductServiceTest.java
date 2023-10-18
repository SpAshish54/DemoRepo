package com.learnings.capstone.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.learnings.capstone.dto.ProductDTO;
import com.learnings.capstone.entity.Product;
import com.learnings.capstone.repository.ProductRepository;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testAddProduct() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        Product product = new Product();
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        productService.addProduct(productDTO);

        // Assert
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testGetProductById() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        // Act
        ProductDTO result = productService.getProductById(productId);

        // Assert
        assertNotNull(result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testGetProductByInternalName() {
        // Arrange
        String internalName = "testInternalName";
        Product product = new Product();
        when(productRepository.getProductEntityByInternalName(anyString())).thenReturn(product);

        // Act
        ProductDTO result = productService.getProductByInternalName(internalName);

        // Assert
        assertNotNull(result);
        verify(productRepository, times(1)).getProductEntityByInternalName(internalName);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<ProductDTO> result = productService.getAllProducts();

        // Assert
        assertEquals(products.size(), result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInternalName("exampleInternalName"); // Set a sample internal name
        Product existingProduct = new Product();

        // Adjust the stubbing to match the specific internal name from the productDTO
        when(productRepository.getProductEntityByInternalName(productDTO.getInternalName())).thenReturn(existingProduct);
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        // Act
        ProductDTO result = productService.updateProduct(productDTO);

        // Assert
        assertNotNull(result);
        verify(productRepository, times(1)).getProductEntityByInternalName(productDTO.getInternalName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        String internalName = "testInternalName";
        Product existingProduct = new Product();
        when(productRepository.getProductEntityByInternalName(anyString())).thenReturn(existingProduct);
        doNothing().when(productRepository).delete(existingProduct);

        // Act
        productService.deleteProduct(internalName);

        // Assert
        verify(productRepository, times(1)).getProductEntityByInternalName(internalName);
        verify(productRepository, times(1)).delete(existingProduct);
    }

//    @Test
//    public void testGetFeaturesByProductId() {
//        // Arrange
//        Long productId = 1L;
//        Product product = new Product();
//        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
//        when(productRepository.getFeaturesByProductId(anyLong())).thenReturn(Arrays.asList(new Feature(), new Feature()));
//
//        // Act
//        List<FeatureDTO> result = productService.getFeaturesByProductId(productId);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        verify(productRepository, times(1)).findById(productId);
//    }

@Test
void testGetProductEntityByInternalName() {
    // Arrange
    String internalName = "exampleInternalName";
    Product expectedProduct = new Product();
    when(productRepository.getProductEntityByInternalName(internalName)).thenReturn(expectedProduct);

    // Act
    Product actualProduct = productService.getProductEntityByInternalName(internalName);

    // Assert
    assertNotNull(actualProduct);
    assertEquals(expectedProduct, actualProduct);
}

//    @Test
//    public void testCreateFeature() {
//        // Arrange
//        FeatureDTO featureDTO = new FeatureDTO();
//        Product product = new Product();
//        when(productRepository.getProductEntityByInternalName(anyString())).thenReturn(product);
//        when(productRepository.save(any(Product.class))).thenReturn(product);
//
//        // Act
//        productService.createFeature("testInternalName", featureDTO);
//
//        // Assert
//        verify(productRepository, times(1)).getProductEntityByInternalName("testInternalName");
//        verify(productRepository, times(1)).save(product);
//    }
//
//    @Test
//    public void testUpdateFeature() {
//        // Arrange
//        FeatureDTO featureDTO = new FeatureDTO();
//        Product product = new Product();
//        Feature existingFeature = new Feature();
//        existingFeature.setId(1L);
//        product.addFeature(existingFeature);
//
//        when(productRepository.getProductEntityByInternalName(anyString())).thenReturn(product);
//        when(productRepository.save(any(Product.class))).thenReturn(product);
//
//        // Act
//        productService.updateFeature("testInternalName", featureDTO);
//
//        // Assert
//        verify(productRepository, times(1)).getProductEntityByInternalName("testInternalName");
//        verify(productRepository, times(1)).save(product);
//    }
//
//    @Test
//    public void testDeleteFeature() {
//        // Arrange
//        String internalName = "testInternalName";
//        Product product = new Product();
//        Feature existingFeature = new Feature();
//        existingFeature.setId(1L);
//        product.addFeature(existingFeature);
//
//        when(productRepository.getProductEntityByInternalName(anyString())).thenReturn(product);
//
//        // Act
//        productService.deleteFeature(internalName, 1L);
//
//        // Assert
//        verify(productRepository, times(1)).getProductEntityByInternalName("testInternalName");
//        verify(productRepository, times(1)).save(product);
//    }
}

