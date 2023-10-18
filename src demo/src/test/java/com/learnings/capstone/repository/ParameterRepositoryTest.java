package com.learnings.capstone.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learnings.capstone.entity.Feature;
import com.learnings.capstone.entity.Parameter;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ParameterRepositoryTest {

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Test
    public void testGetParameterEntityByInternalName() {
        // Arrange
        String internalName = "test-parameter";
        Parameter parameter = new Parameter();
        parameter.setInternalName(internalName);
        parameterRepository.save(parameter);

        // Act
        Parameter foundParameter = parameterRepository.getParameterEntityByInternalName(internalName);

        // Assert
        assertNotNull(foundParameter);
        assertEquals(internalName, foundParameter.getInternalName());
    }

    @Test
    public void testFindByFeatureInternalName() {
        // Arrange
        Feature feature = new Feature();
        feature.setInternalName("test-feature");
        featureRepository.save(feature);

        Parameter parameter = new Parameter();
        parameter.setInternalName("test-parameter");
        parameter.setFeature(feature);
        parameterRepository.save(parameter);

        // Act
        List<Parameter> foundParameters = parameterRepository.findByFeature_InternalName("test-feature");

        // Assert
        assertNotNull(foundParameters);
        assertFalse(foundParameters.isEmpty());
        assertEquals(parameter.getInternalName(), foundParameters.get(0).getInternalName());
    }
}
