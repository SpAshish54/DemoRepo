package com.learnings.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnings.capstone.dto.FeatureDTO;
import com.learnings.capstone.service.FeatureService;

import java.util.List;

@RestController
@RequestMapping("/api/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping("{productInternalName}")
    public ResponseEntity<Void> addFeatureToProduct(@PathVariable String productInternalName, @RequestBody FeatureDTO featureDTO) {
        featureService.addFeatureToProduct(productInternalName, featureDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{internalName}")
    public ResponseEntity<FeatureDTO> getFeatureByInternalName(@PathVariable String internalName) {
        FeatureDTO featureDTO = featureService.getFeatureByInternalName(internalName);
        return ResponseEntity.ok(featureDTO);
    }

    @GetMapping("/product/{productInternalName}")
    public ResponseEntity<List<FeatureDTO>> getFeaturesByProductInternalName(@PathVariable("productInternalName") String productInternalName) {
        List<FeatureDTO> featureDTOs = featureService.getFeaturesByProductInternalName(productInternalName);
        return ResponseEntity.ok(featureDTOs);
    }

    
    @GetMapping
    public ResponseEntity<List<FeatureDTO>> getAllFeatures() {
        List<FeatureDTO> featureDTOs = featureService.getAllFeatures();
        return ResponseEntity.ok(featureDTOs);
    }

    @PutMapping
    public ResponseEntity<FeatureDTO> updateFeature(@RequestBody FeatureDTO featureDTO) {
        FeatureDTO updatedFeature = featureService.updateFeature(featureDTO);
        return ResponseEntity.ok(updatedFeature);
    }
    
    @DeleteMapping
    public ResponseEntity<Void> deleteFeature(@RequestParam String internalName) {
        featureService.deleteFeature(internalName);
        return ResponseEntity.noContent().build();
    }
}

        // @GetMapping("/{featureId}/parameters")
        // public ResponseEntity<List<ParameterDTO>> getParametersByFeatureId(@PathVariable Long featureId) {
        //     List<ParameterDTO> parameterDTOs = featureService.getParametersByFeatureId(featureId);
        //     return ResponseEntity.ok(parameterDTOs);
        // }