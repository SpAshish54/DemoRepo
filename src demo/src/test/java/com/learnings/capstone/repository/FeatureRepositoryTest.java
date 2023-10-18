package com.learnings.capstone.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learnings.capstone.entity.Feature;
import com.learnings.capstone.entity.Product;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FeatureRepositoryTest {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindByProductInternalName() {
        // Arrange
        Product product = new Product();
        product.setInternalName("test-product");
        productRepository.save(product);

        Feature feature = new Feature();
        feature.setInternalName("test-feature");
        feature.setProduct(product);
        featureRepository.save(feature);

        // Act
        List<Feature> foundFeatures = featureRepository.findByProduct_InternalName("test-product");

        // Assert
        assertNotNull(foundFeatures);
        assertFalse(foundFeatures.isEmpty());
        assertEquals(feature.getInternalName(), foundFeatures.get(0).getInternalName());
    }

    @Test
    public void testGetFeatureEntityByInternalName() {
        // Arrange
        String internalName = "test-feature";
        Feature feature = new Feature();
        feature.setInternalName(internalName);
        featureRepository.save(feature);

        // Act
        Feature foundFeature = featureRepository.getFeatureEntityByInternalName(internalName);

        // Assert
        assertNotNull(foundFeature);
        assertEquals(internalName, foundFeature.getInternalName());
    }
}

