package com.rudderstack.sourceapplication.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudderstack.sourceapplication.domains.SourceFormTemplateRequest;
import com.rudderstack.sourceapplication.domains.SourceFormTemplateResponse;
import com.rudderstack.sourceapplication.entity.UserEntity;
import com.rudderstack.sourceapplication.exceptions.BadRequestException;
import com.rudderstack.sourceapplication.exceptions.ResourceNotFoundException;
import com.rudderstack.sourceapplication.repository.SourceFormTemplateRepository;
import com.rudderstack.sourceapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceServiceImpl implements SourceService{
    private final ObjectMapper objectMapper;
    private final SourceFormTemplateRepository sourceFormTemplateRepository;
    private final UserRepository userRepository;

    @Autowired
    public SourceServiceImpl(SourceFormTemplateRepository sourceFormTemplateRepository, UserRepository userRepository) {
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
        return new SourceFormTemplateResponse();
    }
}

