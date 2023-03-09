package com.rudderstack.sourceapplication.domains;

import com.rudderstack.sourceapplication.converters.HashMapConverter;
import jakarta.persistence.Convert;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class SourceFormTemplateResponse implements Serializable {
    private String type;
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> fields;
}