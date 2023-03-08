package com.rudderstack.sourceapplication.mappers;

import com.rudderstack.sourceapplication.domains.SourceFormTemplateRequest;
import com.rudderstack.sourceapplication.domains.SourceFormTemplateResponse;
import com.rudderstack.sourceapplication.domains.SourceResponse;
import com.rudderstack.sourceapplication.entity.SourceEntity;
import com.rudderstack.sourceapplication.entity.SourceFormTemplateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SourceMapper {
    SourceFormTemplateEntity mapSourceFormTemplateEntity(SourceFormTemplateRequest sourceFormTemplateRequest);
    @Mapping(source = "type", target = "type")
    @Mapping(source = "fields", target = "fields")
    SourceFormTemplateResponse mapSourceFormTemplateResponse(SourceFormTemplateEntity sourceFormTemplateEntity);
    @Mapping(source = "sourceFormTemplateEntity.type", target = "sourceType")
    @Mapping(source = "data", target = "values")
    SourceResponse mapSourceResponse(SourceEntity sourceEntity);
}