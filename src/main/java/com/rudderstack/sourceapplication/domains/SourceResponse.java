package com.rudderstack.sourceapplication.domains;

import com.rudderstack.sourceapplication.converters.HashMapConverter;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class SourceResponse {
    @NotBlank
    private String sourceType;
    @NotNull
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> values;
}
