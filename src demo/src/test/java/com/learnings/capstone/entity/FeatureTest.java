package com.learnings.capstone.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class FeatureTest {

    @Test
    public void testFeatureConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String name = "TestFeature";
        String internalName = "test-feature";
        String details = "Feature details";
        Product product = new Product();
        List<Parameter> parameters = new ArrayList<>();

        // Act
        Feature feature = new Feature();
        feature.setId(id);
        feature.setName(name);
        feature.setInternalName(internalName);
        feature.setDetails(details);
        feature.setProduct(product);
        feature.setParameters(parameters);

        // Assert
        assertEquals(id, feature.getId());
        assertEquals(name, feature.getName());
        assertEquals(internalName, feature.getInternalName());
        assertEquals(details, feature.getDetails());
        assertEquals(product, feature.getProduct());
        assertEquals(parameters, feature.getParameters());
    }

    @Test
    public void testFeatureSetters() {
        // Arrange
        Feature feature = new Feature();
        Long id = 1L;
        String name = "TestFeature";
        String internalName = "test-feature";
        String details = "Feature details";
        Product product = new Product();
        List<Parameter> parameters = new ArrayList<>();

        // Act
        feature.setId(id);
        feature.setName(name);
        feature.setInternalName(internalName);
        feature.setDetails(details);
        feature.setProduct(product);
        feature.setParameters(parameters);

        // Assert
        assertEquals(id, feature.getId());
        assertEquals(name, feature.getName());
        assertEquals(internalName, feature.getInternalName());
        assertEquals(details, feature.getDetails());
        assertEquals(product, feature.getProduct());
        assertEquals(parameters, feature.getParameters());
    }

    @Test
    public void testFeatureParameters() {
        // Arrange
        Feature feature = new Feature();
        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter1 = new Parameter();
        Parameter parameter2 = new Parameter();
        parameters.add(parameter1);
        parameters.add(parameter2);

        // Act
        feature.setParameters(parameters);

        // Assert
        assertEquals(parameters, feature.getParameters());
    }
}

