package com.rudderstack.sourceapplication.service.validators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudderstack.sourceapplication.domains.elements.Checkbox;
import com.rudderstack.sourceapplication.enums.ElementType;
import com.rudderstack.sourceapplication.exceptions.InvalidSourceException;
import com.rudderstack.sourceapplication.utils.ObjectMapperUtil;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CheckboxValidator implements ElementValidator {

    private final ObjectMapper objectMapper;
    public CheckboxValidator() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void parseAndValidateRequestBody(Object request) {
        Checkbox checkbox = ObjectMapperUtil.parse(request, Checkbox.class, objectMapper);
    }
    @Override
    public void validateValue(Object request, Object value){
        Checkbox checkbox = ObjectMapperUtil.parse(request, Checkbox.class, objectMapper);
        if(checkbox.isRequired() && Objects.isNull(value)){
            throw new InvalidSourceException("Checkbox validation failed");
        }
        if(!Objects.isNull(value)){
            ObjectMapperUtil.parse(value, Boolean.class, objectMapper);
        }
    }
    @Override
    public ElementType getElementType() {
        return ElementType.CHECKBOX;
    }


}