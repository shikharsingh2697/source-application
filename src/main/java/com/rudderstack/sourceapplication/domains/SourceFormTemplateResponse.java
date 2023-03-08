package com.rudderstack.sourceapplication.domains;

import com.rudderstack.sourceapplication.converters.HashMapConverter;
import jakarta.persistence.Convert;
import lombok.Data;

import java.util.Map;

@Data
public class SourceFormTemplateResponse {
    private String type;
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> fields;
}