package com.rudderstack.sourceapplication.service;

import com.rudderstack.sourceapplication.domains.*;
import com.rudderstack.sourceapplication.entity.SourceEntity;
import com.rudderstack.sourceapplication.entity.SourceFormTemplateEntity;
import com.rudderstack.sourceapplication.entity.UserEntity;
import com.rudderstack.sourceapplication.mappers.SourceMapper;
import com.rudderstack.sourceapplication.repository.SourceFormTemplateRepository;
import com.rudderstack.sourceapplication.repository.SourceRepository;
import com.rudderstack.sourceapplication.repository.UserRepository;
import com.rudderstack.sourceapplication.service.validators.InputValidator;
import com.rudderstack.sourceapplication.utils.FormElementUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.rudderstack.sourceapplication.util.SourceServiceUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SourceServiceImplTests {
    private SourceFormTemplateRepository sourceFormTemplateRepository;

    private SourceRepository sourceRepository;

    private UserRepository userRepository;

    private FormElementUtil formElementUtil;
    private SourceMapper sourceMapper;

    private SourceServiceImpl sourceService;

    @BeforeEach
    public void setup(){
        sourceMapper = mock(SourceMapper.class);
        sourceFormTemplateRepository = mock(SourceFormTemplateRepository.class);
        userRepository = mock(UserRepository.class);
        formElementUtil = mock(FormElementUtil.class);
        sourceRepository = mock(SourceRepository.class);
        sourceService = new SourceServiceImpl(sourceFormTemplateRepository, userRepository, formElementUtil, sourceMapper, sourceRepository);
    }
//    @Test
//    public void createSourceFormTemplate_notAdmin_throwsException(){
//        when(userRepository.findById(any())).thenReturn(Optional.of(new UserEntity().setAdmin(false)));
//        Assert.assertThrows(BadRequestException.class, ()->sourceService.createSourceFormTemplate(new SourceFormTemplateRequest(), 1L));
//    }

    // TEST WHEN ADDING SOURCE FORM TEMPLATE FOR ADMIN USER
//    @Test
//    public void createSourceFormTemplate_admin_success(){
//        when(userRepository.findById(any())).thenReturn(Optional.of(new UserEntity().setAdmin(true)));
//        when(sourceFormTemplateRepository.save(any())).thenReturn(new SourceFormTemplateRequest());
//        Assert.assertNotNull(sourceService.createSourceFormTemplate(new SourceFormTemplateRequest(), 1L));
//    }


    // test createSourceFormTemplate with valid type
    @Test
    public void createSourceFormTemplate_validType_success(){
        SourceFormTemplateResponse expectedResponse = createSourceFormTemplateResponse();
        SourceFormTemplateRequest request = createSourceFormTemplateRequest();
        UserEntity userEntity = createUserEntity();
        SourceFormTemplateEntity sourceFormTemplateEntity = createSourceFormTemplateEntity();
        when(userRepository.findById(any())).thenReturn(Optional.of(new UserEntity().setAdmin(true)));
        when(sourceFormTemplateRepository.save(any())).thenReturn(request);
        when (formElementUtil.getFormElementValidator(any())).thenReturn(new InputValidator());
        when (sourceMapper.mapSourceFormTemplateEntity(request)).thenReturn(sourceFormTemplateEntity);
        when (sourceMapper.mapSourceFormTemplateResponse(any())).thenReturn(expectedResponse);
        SourceFormTemplateResponse actualResponse = sourceService.createSourceFormTemplate(request, 1L);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void fetchSourceFormTemplate_success(){
        SourceFormTemplateResponse expectedResponse = createSourceFormTemplateResponse();
        SourceFormTemplateEntity sourceFormTemplateEntity = createSourceFormTemplateEntity();
        when(sourceFormTemplateRepository.findByType(anyString())).thenReturn(Optional.of(sourceFormTemplateEntity));
        when (sourceMapper.mapSourceFormTemplateResponse(any())).thenReturn(expectedResponse);
        SourceFormTemplateResponse actualResponse = sourceService.fetchSourceFormTemplate("source1");
        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    public void fetchAllSourceTypes_success(){
        SourceFormTemplateEntity sourceFormTemplateEntity = createSourceFormTemplateEntity();
        when(sourceFormTemplateRepository.findAll()).thenReturn(Lists.newArrayList(sourceFormTemplateEntity));
        List<SourceDetails> actualResponse = sourceService.fetchAllSourceTypes();
        assertEquals(1, actualResponse.size());
        assertEquals("source1", actualResponse.get(0).getValue());
    }

    @Test
    public void createSource_success(){
        SourceRequest sourceRequest = createSourceRequest();
        UserEntity userEntity = createUserEntity();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        SourceFormTemplateEntity sourceFormTemplateEntity = createSourceFormTemplateEntity();
        when(sourceFormTemplateRepository.findByType(anyString())).thenReturn(Optional.of(sourceFormTemplateEntity));
        when(formElementUtil.getFormElementValidator(any())).thenReturn(new InputValidator());
        when(sourceRepository.saveAndFlush(any())).thenReturn(new SourceEntity());
        when(sourceMapper.mapSourceResponse(any())).thenReturn(createSourceResponse());
        SourceResponse actualResponse = sourceService.createSource(sourceRequest, 1L);
        assertEquals("source2", actualResponse.getSourceType());
    }
}