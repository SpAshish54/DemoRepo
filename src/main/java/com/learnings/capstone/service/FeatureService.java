package com.learnings.capstone.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnings.capstone.dto.FeatureDTO;
import com.learnings.capstone.entity.Feature;
import com.learnings.capstone.entity.Product;
import com.learnings.capstone.exception.CatalogBusinessException;
import com.learnings.capstone.repository.FeatureRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ProductService productService; 

    public void addFeatureToProduct(Long productId, FeatureDTO featureDTO) {
        Product product = productService.getProductEntityById(productId);

        Feature feature = convertDTOToEntity(featureDTO);
        feature.setProduct(product);

        featureRepository.save(feature);
    }

    public FeatureDTO getFeatureById(Long featureId) {
        Feature feature = getFeatureEntityById(featureId);
        return convertEntityToDTO(feature);
    }

    public List<FeatureDTO> getAllFeatures() {
        List<Feature> features = featureRepository.findAll();
        return features.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public FeatureDTO updateFeature(Long featureId, FeatureDTO featureDTO) {
        Feature existingFeature = getFeatureEntityById(featureId);
        updateFeatureFromDTO(existingFeature, featureDTO);
        featureRepository.save(existingFeature);
        return convertEntityToDTO(existingFeature);
    }

    public void deleteFeature(Long featureId) {
        Feature existingFeature = getFeatureEntityById(featureId);
        featureRepository.delete(existingFeature);
    }

    public Feature convertDTOToEntity(FeatureDTO featureDTO) {
        Feature feature = new Feature();
        BeanUtils.copyProperties(featureDTO, feature);
        // Set other properties as needed
        return feature;
    }

    public FeatureDTO convertEntityToDTO(Feature feature) {
        FeatureDTO featureDTO = new FeatureDTO();
        BeanUtils.copyProperties(feature, featureDTO);
        // Set other properties as needed
        return featureDTO;
    }

    public void updateFeatureFromDTO(Feature feature, FeatureDTO featureDTO) {
        BeanUtils.copyProperties(featureDTO, feature);
        // Update other properties as needed
    }

    public Feature getFeatureEntityById(Long featureId) {
        return featureRepository.findById(featureId)
                .orElseThrow(() -> new CatalogBusinessException("Feature not found with id " + featureId));
    }
}
