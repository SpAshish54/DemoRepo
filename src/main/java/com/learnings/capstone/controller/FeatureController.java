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

    @PostMapping("/product/{productId}")
    public ResponseEntity<Void> addFeatureToProduct(@PathVariable Long productId, @RequestBody FeatureDTO featureDTO) {
        featureService.addFeatureToProduct(productId, featureDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{featureId}")
    public ResponseEntity<FeatureDTO> getFeatureById(@PathVariable Long featureId) {
        FeatureDTO featureDTO = featureService.getFeatureById(featureId);
        return ResponseEntity.ok(featureDTO);
    }

    @GetMapping
    public ResponseEntity<List<FeatureDTO>> getAllFeatures() {
        List<FeatureDTO> featureDTOs = featureService.getAllFeatures();
        return ResponseEntity.ok(featureDTOs);
    }

    @PutMapping("/{featureId}")
    public ResponseEntity<FeatureDTO> updateFeature(@PathVariable Long featureId, @RequestBody FeatureDTO featureDTO) {
        FeatureDTO updatedFeature = featureService.updateFeature(featureId, featureDTO);
        return ResponseEntity.ok(updatedFeature);
    }

    @DeleteMapping("/{featureId}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Long featureId) {
        featureService.deleteFeature(featureId);
        return ResponseEntity.noContent().build();
    }
}
