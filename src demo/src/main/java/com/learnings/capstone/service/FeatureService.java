package com.learnings.capstone.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnings.capstone.dto.FeatureDTO;
import com.learnings.capstone.dto.ParameterDTO;
import com.learnings.capstone.entity.Feature;
import com.learnings.capstone.entity.Parameter;
import com.learnings.capstone.entity.Product;
import com.learnings.capstone.repository.FeatureRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ProductService productService; 

    public void addFeatureToProduct(String productInternalName, FeatureDTO featureDTO) {
        Product product = productService.getProductEntityByInternalName(productInternalName);

        Feature feature = convertDTOToEntity(featureDTO);
        feature.setProduct(product);

        featureRepository.save(feature);
    }

    
    public FeatureDTO getFeatureByInternalName(String featureInternalName) {
        Feature feature = getFeatureEntityByInternalName(featureInternalName);
        return convertEntityToDTO(feature);
    }
    
    
    
    public List<FeatureDTO> getAllFeatures() {
        List<Feature> features = featureRepository.findAll();
        return features.stream()
        .map(this::convertEntityToDTO)
        .collect(Collectors.toList());
    }

    public FeatureDTO updateFeature(FeatureDTO featureDTO) {
        Feature existingFeature = featureRepository.getFeatureEntityByInternalName(featureDTO.getInternalName());
        updateFeatureFromDTO(existingFeature, featureDTO);
        featureRepository.save(existingFeature);
        return convertEntityToDTO(existingFeature);
    }
    
    public void deleteFeature(String internalName) {
        Feature existingFeature = featureRepository.getFeatureEntityByInternalName(internalName);
        featureRepository.delete(existingFeature);
    }
    
    public Feature convertDTOToEntity(FeatureDTO featureDTO) {
        Feature feature = new Feature();
        BeanUtils.copyProperties(featureDTO, feature);
        return feature;
    }
    
    public FeatureDTO convertEntityToDTO(Feature feature) {
        FeatureDTO featureDTO = new FeatureDTO();
        BeanUtils.copyProperties(feature, featureDTO);
        return featureDTO;
    }
    
    public void updateFeatureFromDTO(Feature feature, FeatureDTO featureDTO) {
        BeanUtils.copyProperties(featureDTO, feature);
    }
    
    
    public List<ParameterDTO> getParametersByFeatureInternalName(String featureInternalName) {
        Feature feature = getFeatureEntityByInternalName(featureInternalName);
        List<ParameterDTO> parameterDTOs = feature.getParameters().stream()
        .map(this::convertParameterEntityToDTO)
        .collect(Collectors.toList());
        return parameterDTOs;
    }
    
    
    public Feature getFeatureEntityByInternalName(String featureInternalName) {
        return featureRepository.getFeatureEntityByInternalName(featureInternalName);
    }
    
    private ParameterDTO convertParameterEntityToDTO(Parameter parameter) {
        ParameterDTO parameterDTO = new ParameterDTO();
        BeanUtils.copyProperties(parameter, parameterDTO);
        return parameterDTO;
    }
    
    public List<FeatureDTO> getFeaturesByProductInternalName(String productInternalName) {
        List<Feature> features = featureRepository.findByProduct_InternalName(productInternalName);
        return features.stream()
            .map(this::convertEntityToDTO)
            .collect(Collectors.toList());
    }
}

        // public FeatureDTO getFeatureById(Long featureId) {
        //     Feature feature = getFeatureEntityById(featureId);
        //     return convertEntityToDTO(feature);
        // }
        // public Feature getFeatureEntityById(Long featureId) {
            //     return featureRepository.findById(featureId)
            //             .orElseThrow(() -> new CatalogBusinessException("Feature not found with id " + featureId));
            // }
            
            // public List<ParameterDTO> getParametersByFeatureId(Long featureId) {
                //     Feature feature = getFeatureEntityById(featureId);
                //     List<ParameterDTO> parameterDTOs = feature.getParameters().stream()
        //             .map(this::convertParameterEntityToDTO)
        //             .collect(Collectors.toList());
        //     return parameterDTOs;
        // }