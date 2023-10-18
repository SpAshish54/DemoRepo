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

    @PostMapping("/{featureInternalName}")
    public ResponseEntity<Void> addParameterToFeature(@PathVariable String featureInternalName, @RequestBody ParameterDTO parameterDTO) {
        parameterService.addParameterToFeature(featureInternalName, parameterDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{parameterId}")
    public ResponseEntity<ParameterDTO> getParameterByInternalName(@PathVariable String parameterInternalName) {
        ParameterDTO parameterDTO = parameterService.getParameterByInternalName(parameterInternalName);
        return ResponseEntity.ok(parameterDTO);
    }

    @GetMapping("/feature/{featureInternalName}")
    public ResponseEntity<List<ParameterDTO>> getParametersByFeatureInternalName(@PathVariable("featureInternalName") String featureInternalName) {
        List<ParameterDTO> parameterDTOs = parameterService.getParametersByFeatureInternalName(featureInternalName);
        return ResponseEntity.ok(parameterDTOs);
    }

    @GetMapping
    public ResponseEntity<List<ParameterDTO>> getAllParameters() {
        List<ParameterDTO> parameterDTOs = parameterService.getAllParameters();
        return ResponseEntity.ok(parameterDTOs);
    }

    @PutMapping
    public ResponseEntity<ParameterDTO> updateParameter(@RequestBody ParameterDTO parameterDTO) {
        ParameterDTO updatedParameter = parameterService.updateParameter(parameterDTO);
        return ResponseEntity.ok(updatedParameter);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteParameter(@RequestParam String internalName) {
        parameterService.deleteParameter(internalName);
        return ResponseEntity.noContent().build();
    }
}
