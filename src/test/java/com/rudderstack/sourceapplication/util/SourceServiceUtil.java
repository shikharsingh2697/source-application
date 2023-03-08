package com.rudderstack.sourceapplication.util;

import com.rudderstack.sourceapplication.domains.*;
import com.rudderstack.sourceapplication.entity.SourceFormTemplateEntity;
import com.rudderstack.sourceapplication.entity.UserEntity;
import com.rudderstack.sourceapplication.enums.ElementType;

import java.util.HashMap;
import java.util.Map;

public class SourceServiceUtil {
    private SourceServiceUtil() {
    }

    public static final Map<String, Object> fields = new HashMap<>()
    {{
        put("field1", createFieldDetails());
    }};

    public static FieldDetails createFieldDetails() {
        FieldDetails fieldDetails = new FieldDetails();
        fieldDetails.setType(ElementType.INPUT);
        return fieldDetails;
    }
    public static SourceFormTemplateResponse createSourceFormTemplateResponse() {
        SourceFormTemplateResponse response = new SourceFormTemplateResponse();
        response.setType("source1");

        response.setFields(fields);
        return response;
    }

    public static SourceFormTemplateRequest createSourceFormTemplateRequest() {
        SourceFormTemplateRequest request = new SourceFormTemplateRequest();
        request.setType("source1");
        request.setFields(fields);
        return request;
    }

    public static SourceRequest createSourceRequest(){
        SourceRequest request = new SourceRequest();
        Map<String, Object> map = new HashMap<>();
        map.put("regex", "[a-z0-9]");
        request.setValues(map);
        request.setSourceType("source1");
        return request;
    }

    // mock user entity
    public static UserEntity createUserEntity()
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setEmailId("test");
        userEntity.setAdmin(true);
        return userEntity;
    }

    public static SourceResponse createSourceResponse(){
        SourceResponse sourceResponse = new SourceResponse();
        sourceResponse.setSourceType("source2");
        return sourceResponse;
    }

    // mock elementValidator
//    public static ElementValidator createElementValidator()
//    {
//        return new ElementValidator();
//    }

    // mock SourceFormTemplateEntity
    public static SourceFormTemplateEntity createSourceFormTemplateEntity()
    {
        SourceFormTemplateEntity sourceFormTemplateEntity = new SourceFormTemplateEntity();
        sourceFormTemplateEntity.setType("source1");
        sourceFormTemplateEntity.setFields(fields);
        return sourceFormTemplateEntity;
    }
}
