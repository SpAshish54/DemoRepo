package com.learnings.capstone.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.learnings.capstone.dto.FeatureDTO;
import com.learnings.capstone.service.FeatureService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FeatureControllerTest {

    @InjectMocks
    private FeatureController featureController;

    @Mock
    private FeatureService featureService;

    @Test
    public void testAddFeatureToProduct() {
        // Arrange
        String productInternalName = "sampleInternalName";
        FeatureDTO featureDTO = new FeatureDTO();
        doNothing().when(featureService).addFeatureToProduct(productInternalName, featureDTO);

        // Act
        ResponseEntity<Void> response = featureController.addFeatureToProduct(productInternalName, featureDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(featureService, times(1)).addFeatureToProduct(productInternalName, featureDTO);
    }

    @Test
    public void testGetFeatureByInternalName() {
        // Arrange
        String internalName = "sampleInternalName";
        FeatureDTO expectedFeatureDTO = new FeatureDTO();
        when(featureService.getFeatureByInternalName(internalName)).thenReturn(expectedFeatureDTO);

        // Act
        ResponseEntity<FeatureDTO> response = featureController.getFeatureByInternalName(internalName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFeatureDTO, response.getBody());
        verify(featureService, times(1)).getFeatureByInternalName(internalName);
    }

    @Test
    public void testGetFeaturesByProductInternalName() {
        // Arrange
        String productInternalName = "sampleProductInternalName";
        List<FeatureDTO> expectedFeatureDTOs = Arrays.asList(new FeatureDTO(), new FeatureDTO());
        when(featureService.getFeaturesByProductInternalName(productInternalName)).thenReturn(expectedFeatureDTOs);

        // Act
        ResponseEntity<List<FeatureDTO>> response = featureController.getFeaturesByProductInternalName(productInternalName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFeatureDTOs, response.getBody());
        verify(featureService, times(1)).getFeaturesByProductInternalName(productInternalName);
    }

    @Test
    public void testGetAllFeatures() {
        // Arrange
        List<FeatureDTO> expectedFeatureDTOs = Arrays.asList(new FeatureDTO(), new FeatureDTO());
        when(featureService.getAllFeatures()).thenReturn(expectedFeatureDTOs);

        // Act
        ResponseEntity<List<FeatureDTO>> response = featureController.getAllFeatures();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFeatureDTOs, response.getBody());
        verify(featureService, times(1)).getAllFeatures();
    }

    @Test
    public void testUpdateFeature() {
        // Arrange
        FeatureDTO featureDTO = new FeatureDTO();
        when(featureService.updateFeature(featureDTO)).thenReturn(featureDTO);

        // Act
        ResponseEntity<FeatureDTO> response = featureController.updateFeature(featureDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(featureDTO, response.getBody());
        verify(featureService, times(1)).updateFeature(featureDTO);
    }

    @Test
    public void testDeleteFeature() {
        // Arrange
        String internalName = "sampleInternalName";
        doNothing().when(featureService).deleteFeature(internalName);

        // Act
        ResponseEntity<Void> response = featureController.deleteFeature(internalName);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(featureService, times(1)).deleteFeature(internalName);
    }
}
