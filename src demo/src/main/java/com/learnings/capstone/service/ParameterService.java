package com.learnings.capstone.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnings.capstone.dto.ParameterDTO;
import com.learnings.capstone.entity.Feature;
import com.learnings.capstone.entity.Parameter;
import com.learnings.capstone.repository.ParameterRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private FeatureService featureService; 

    public void addParameterToFeature(String featureInternalName, ParameterDTO parameterDTO) {
        Feature feature = featureService.getFeatureEntityByInternalName(featureInternalName);

        Parameter parameter = convertDTOToEntity(parameterDTO);
        parameter.setFeature(feature);

        parameterRepository.save(parameter);

    }

    public ParameterDTO getParameterByInternalName(String parameterInternalName) {
        Parameter parameter = getParameterEntityByInternalName(parameterInternalName);
        return convertEntityToDTO(parameter);
    }

    public List<ParameterDTO> getAllParameters() {
        List<Parameter> parameters = parameterRepository.findAll();
        return parameters.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public ParameterDTO updateParameter(ParameterDTO parameterDTO) {
        Parameter existingParameter = parameterRepository.getParameterEntityByInternalName(parameterDTO.getInternalName());
        updateParameterFromDTO(existingParameter, parameterDTO);
        parameterRepository.save(existingParameter);
        return convertEntityToDTO(existingParameter);
    }

    public void deleteParameter(String productInternalName) {
        Parameter existingParameter = parameterRepository.getParameterEntityByInternalName(productInternalName);
        parameterRepository.delete(existingParameter);
    }

    private Parameter convertDTOToEntity(ParameterDTO parameterDTO) {
        Parameter parameter = new Parameter();
        BeanUtils.copyProperties(parameterDTO, parameter);
        // Set other properties as needed
        return parameter;
    }

    private ParameterDTO convertEntityToDTO(Parameter parameter) {
        ParameterDTO parameterDTO = new ParameterDTO();
        BeanUtils.copyProperties(parameter, parameterDTO);
        // Set other properties as needed
        return parameterDTO;
    }

    private void updateParameterFromDTO(Parameter parameter, ParameterDTO parameterDTO) {
        BeanUtils.copyProperties(parameterDTO, parameter);
        // Update other properties as needed
    }

    private Parameter getParameterEntityByInternalName(String featureInternalName) {
        return parameterRepository.getParameterEntityByInternalName(featureInternalName);
    }

    public List<ParameterDTO> getParametersByFeatureInternalName(String featureInternalName){
        List<Parameter> parameters = parameterRepository.findByFeature_InternalName(featureInternalName);
        return parameters.stream()
            .map(this::convertEntityToDTO)
            .collect(Collectors.toList());
    }
}
