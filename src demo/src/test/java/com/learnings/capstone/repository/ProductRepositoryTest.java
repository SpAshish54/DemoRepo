package com.learnings.capstone.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learnings.capstone.entity.Product;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testGetProductEntityByInternalName() {
        // Arrange
        String internalName = "test-product";
        Product product = new Product();
        product.setInternalName(internalName);
        productRepository.save(product);

        // Act
        Product foundProduct = productRepository.getProductEntityByInternalName(internalName);

        // Assert
        assertNotNull(foundProduct);
        assertEquals(internalName, foundProduct.getInternalName());
    }
}
