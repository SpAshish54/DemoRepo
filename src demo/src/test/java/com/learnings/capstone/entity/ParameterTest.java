package com.learnings.capstone.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ParameterTest {

    @Test
    public void testParameterConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String name = "TestParameter";
        String internalName = "test-parameter";
        String details = "Parameter details";
        ParameterType parameterType = ParameterType.QUANTITY;
        Feature feature = new Feature();
        // Act
        Parameter parameter = new Parameter();
        parameter.setId(id);
        parameter.setName(name);
        parameter.setInternalName(internalName);
        parameter.setDetails(details);
        parameter.setParameterType(parameterType);
        parameter.setFeature(feature);

        // Assert
        assertEquals(id, parameter.getId());
        assertEquals(name, parameter.getName());
        assertEquals(internalName, parameter.getInternalName());
        assertEquals(details, parameter.getDetails());
        assertEquals(parameterType, parameter.getParameterType());
        assertEquals(feature, parameter.getFeature());
    }

    @Test
    public void testParameterSetters() {
        // Arrange
        Parameter parameter = new Parameter();
        Long id = 1L;
        String name = "TestParameter";
        String internalName = "test-parameter";
        String details = "Parameter details";
        ParameterType parameterType = ParameterType.QUANTITY;
        Feature feature = new Feature();

        // Act
        parameter.setId(id);
        parameter.setName(name);
        parameter.setInternalName(internalName);
        parameter.setDetails(details);
        parameter.setParameterType(parameterType);
        parameter.setFeature(feature);

        // Assert
        assertEquals(id, parameter.getId());
        assertEquals(name, parameter.getName());
        assertEquals(internalName, parameter.getInternalName());
        assertEquals(details, parameter.getDetails());
        assertEquals(parameterType, parameter.getParameterType());
        assertEquals(feature, parameter.getFeature());
    }
}

