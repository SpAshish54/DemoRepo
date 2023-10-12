package com.learnings.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnings.capstone.dto.ParameterDTO;
import com.learnings.capstone.service.ParameterService;

import java.util.List;

@RestController
@RequestMapping("/api/parameters")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    @PostMapping("/feature/{featureId}")
    public ResponseEntity<Void> addParameterToFeature(@PathVariable Long featureId, @RequestBody ParameterDTO parameterDTO) {
        parameterService.addParameterToFeature(featureId, parameterDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{parameterId}")
    public ResponseEntity<ParameterDTO> getParameterById(@PathVariable Long parameterId) {
        ParameterDTO parameterDTO = parameterService.getParameterById(parameterId);
        return ResponseEntity.ok(parameterDTO);
    }

    @GetMapping
    public ResponseEntity<List<ParameterDTO>> getAllParameters() {
        List<ParameterDTO> parameterDTOs = parameterService.getAllParameters();
        return ResponseEntity.ok(parameterDTOs);
    }

    @PutMapping("/{parameterId}")
    public ResponseEntity<ParameterDTO> updateParameter(@PathVariable Long parameterId, @RequestBody ParameterDTO parameterDTO) {
        ParameterDTO updatedParameter = parameterService.updateParameter(parameterId, parameterDTO);
        return ResponseEntity.ok(updatedParameter);
    }

    @DeleteMapping("/{parameterId}")
    public ResponseEntity<Void> deleteParameter(@PathVariable Long parameterId) {
        parameterService.deleteParameter(parameterId);
        return ResponseEntity.noContent().build();
    }
}
