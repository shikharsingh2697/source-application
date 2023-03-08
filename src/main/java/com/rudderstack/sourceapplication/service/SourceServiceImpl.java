package com.rudderstack.sourceapplication.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudderstack.sourceapplication.domains.*;
import com.rudderstack.sourceapplication.entity.SourceEntity;
import com.rudderstack.sourceapplication.entity.SourceFormTemplateEntity;
import com.rudderstack.sourceapplication.entity.UserEntity;
import com.rudderstack.sourceapplication.exceptions.BadRequestException;
import com.rudderstack.sourceapplication.exceptions.ResourceNotFoundException;
import com.rudderstack.sourceapplication.mappers.SourceMapper;
import com.rudderstack.sourceapplication.repository.SourceFormTemplateRepository;
import com.rudderstack.sourceapplication.repository.SourceRepository;
import com.rudderstack.sourceapplication.repository.UserRepository;
import com.rudderstack.sourceapplication.service.validators.ElementValidator;
import com.rudderstack.sourceapplication.utils.FormElementUtil;
import com.rudderstack.sourceapplication.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SourceServiceImpl implements SourceService{
    private final ObjectMapper objectMapper;
    private final SourceFormTemplateRepository sourceFormTemplateRepository;
    private final UserRepository userRepository;
    private final FormElementUtil formElementUtil;
    private final SourceMapper sourceMapper;
    private final SourceRepository sourceRepository;

    @Autowired
    public SourceServiceImpl(SourceFormTemplateRepository sourceFormTemplateRepository, UserRepository userRepository, FormElementUtil formElementUtil, SourceMapper sourceMapper, SourceRepository sourceRepository) {
        this.formElementUtil = formElementUtil;
        this.sourceMapper = sourceMapper;
        this.sourceRepository = sourceRepository;
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.sourceFormTemplateRepository = sourceFormTemplateRepository;
        this.userRepository = userRepository;
    }

    public SourceFormTemplateResponse createSourceFormTemplate(SourceFormTemplateRequest sourceFormTemplateRequest, Long userId){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow( () -> new ResourceNotFoundException("Invalid User"));
        if(!userEntity.isAdmin()){
            throw new BadRequestException("Only Admins can create templates");
        }
        if(sourceFormTemplateRepository.existsByType(sourceFormTemplateRequest.getType())){
            throw new BadRequestException("Template for this source type already exists");
        }
        for (Map.Entry<String, Object> entry: sourceFormTemplateRequest.getFields().entrySet()) {
            FieldDetails fieldDetails = ObjectMapperUtil.parse(entry.getValue(), FieldDetails.class, objectMapper);
            ElementValidator formElement = formElementUtil.getFormElementValidator(fieldDetails.getType());
            formElement.parseAndValidateRequestBody(entry.getValue());//TODO
        }
        SourceFormTemplateEntity sourceFormTemplateEntity = sourceMapper.mapSourceFormTemplateEntity(sourceFormTemplateRequest);
        sourceFormTemplateRepository.saveAndFlush(sourceFormTemplateEntity);
        return sourceMapper.mapSourceFormTemplateResponse(sourceFormTemplateEntity);
    }

    public SourceFormTemplateResponse fetchSourceFormTemplate(String sourceType){
        SourceFormTemplateEntity sourceFormTemplateEntity = sourceFormTemplateRepository.findByType(sourceType).orElseThrow(() -> new ResourceNotFoundException("Source type not found"));
        return sourceMapper.mapSourceFormTemplateResponse(sourceFormTemplateEntity);
    }

    public List<SourceDetails> fetchAllSourceTypes(){
        List<SourceDetails> sourceDetails = new ArrayList<>();
        List<String> sourceTypes = sourceFormTemplateRepository.findAll().stream().map(SourceFormTemplateEntity::getType).collect(Collectors.toList());
        for(String sourceType : sourceTypes){
            sourceDetails.add(new SourceDetails(sourceType, sourceType));
        }
        return sourceDetails;
    }

    @Override
    public SourceResponse createSource(SourceRequest sourceRequest, Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow( () -> new ResourceNotFoundException("Invalid User"));
        SourceFormTemplateEntity sourceFormTemplateEntity = sourceFormTemplateRepository.findByType(sourceRequest.getSourceType()).orElseThrow(() -> new ResourceNotFoundException("Invalid source type"));
        Map<String, Object> sourceValues = sourceRequest.getValues();
        for (Map.Entry<String, Object> entry: sourceFormTemplateEntity.getFields().entrySet()) {
            Object fieldValue = sourceValues.get(entry.getKey());
            FieldDetails fieldDetails = ObjectMapperUtil.parse(entry.getValue(), FieldDetails.class, objectMapper);
            ElementValidator formElement = formElementUtil.getFormElementValidator(fieldDetails.getType());
            formElement.validateValue(entry.getValue(), fieldValue);
        }
        SourceEntity sourceEntity = new SourceEntity().setUserEntity(userEntity).setSourceFormTemplateEntity(sourceFormTemplateEntity).setData(sourceRequest.getValues());
        sourceRepository.saveAndFlush(sourceEntity);
        return sourceMapper.mapSourceResponse(sourceEntity);
    }
}

