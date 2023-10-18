package com.learnings.capstone.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.learnings.capstone.dto.ParameterDTO;
import com.learnings.capstone.service.ParameterService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ParameterControllerTest {

    @InjectMocks
    private ParameterController parameterController;

    @Mock
    private ParameterService parameterService;

    @Test
    public void testAddParameterToFeature() {
        // Arrange
        String featureInternalName = "sampleFeatureInternalName";
        ParameterDTO parameterDTO = new ParameterDTO();
        doNothing().when(parameterService).addParameterToFeature(featureInternalName, parameterDTO);

        // Act
        ResponseEntity<Void> response = parameterController.addParameterToFeature(featureInternalName, parameterDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(parameterService, times(1)).addParameterToFeature(featureInternalName, parameterDTO);
    }

    @Test
    public void testGetParameterByInternalName() {
        // Arrange
        String parameterInternalName = "sampleParameterInternalName";
        ParameterDTO expectedParameterDTO = new ParameterDTO();
        when(parameterService.getParameterByInternalName(parameterInternalName)).thenReturn(expectedParameterDTO);

        // Act
        ResponseEntity<ParameterDTO> response = parameterController.getParameterByInternalName(parameterInternalName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedParameterDTO, response.getBody());
        verify(parameterService, times(1)).getParameterByInternalName(parameterInternalName);
    }

    @Test
    public void testGetParametersByFeatureInternalName() {
        // Arrange
        String featureInternalName = "sampleFeatureInternalName";
        List<ParameterDTO> expectedParameterDTOs = Arrays.asList(new ParameterDTO(), new ParameterDTO());
        when(parameterService.getParametersByFeatureInternalName(featureInternalName)).thenReturn(expectedParameterDTOs);

        // Act
        ResponseEntity<List<ParameterDTO>> response = parameterController.getParametersByFeatureInternalName(featureInternalName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedParameterDTOs, response.getBody());
        verify(parameterService, times(1)).getParametersByFeatureInternalName(featureInternalName);
    }

    @Test
    public void testGetAllParameters() {
        // Arrange
        List<ParameterDTO> expectedParameterDTOs = Arrays.asList(new ParameterDTO(), new ParameterDTO());
        when(parameterService.getAllParameters()).thenReturn(expectedParameterDTOs);

        // Act
        ResponseEntity<List<ParameterDTO>> response = parameterController.getAllParameters();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedParameterDTOs, response.getBody());
        verify(parameterService, times(1)).getAllParameters();
    }

    @Test
    public void testUpdateParameter() {
        // Arrange
        ParameterDTO parameterDTO = new ParameterDTO();
        when(parameterService.updateParameter(parameterDTO)).thenReturn(parameterDTO);

        // Act
        ResponseEntity<ParameterDTO> response = parameterController.updateParameter(parameterDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(parameterDTO, response.getBody());
        verify(parameterService, times(1)).updateParameter(parameterDTO);
    }

    @Test
    public void testDeleteParameter() {
        // Arrange
        String internalName = "sampleInternalName";
        doNothing().when(parameterService).deleteParameter(internalName);

        // Act
        ResponseEntity<Void> response = parameterController.deleteParameter(internalName);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(parameterService, times(1)).deleteParameter(internalName);
    }
}

