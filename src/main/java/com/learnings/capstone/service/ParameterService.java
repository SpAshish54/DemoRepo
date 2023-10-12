package com.learnings.capstone.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnings.capstone.dto.ParameterDTO;
import com.learnings.capstone.entity.Feature;
import com.learnings.capstone.entity.Parameter;
import com.learnings.capstone.exception.CatalogBusinessException;
import com.learnings.capstone.repository.ParameterRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private FeatureService featureService; // Assuming FeatureService is another service for features

    public void addParameterToFeature(Long featureId, ParameterDTO parameterDTO) {
        Feature feature = featureService.getFeatureEntityById(featureId);

        Parameter parameter = convertDTOToEntity(parameterDTO);
        parameter.setFeature(feature);

        parameterRepository.save(parameter);
    }

    public ParameterDTO getParameterById(Long parameterId) {
        Parameter parameter = getParameterEntityById(parameterId);
        return convertEntityToDTO(parameter);
    }

    public List<ParameterDTO> getAllParameters() {
        List<Parameter> parameters = parameterRepository.findAll();
        return parameters.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public ParameterDTO updateParameter(Long parameterId, ParameterDTO parameterDTO) {
        Parameter existingParameter = getParameterEntityById(parameterId);
        updateParameterFromDTO(existingParameter, parameterDTO);
        parameterRepository.save(existingParameter);
        return convertEntityToDTO(existingParameter);
    }

    public void deleteParameter(Long parameterId) {
        Parameter existingParameter = getParameterEntityById(parameterId);
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

    private Parameter getParameterEntityById(Long parameterId) {
        return parameterRepository.findById(parameterId)
                .orElseThrow(() -> new CatalogBusinessException("Parameter not found with id " + parameterId));
    }
}
