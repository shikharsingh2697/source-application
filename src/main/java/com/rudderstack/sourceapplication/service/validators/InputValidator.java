package com.rudderstack.sourceapplication.service.validators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudderstack.sourceapplication.domains.elements.Input;
import com.rudderstack.sourceapplication.enums.ElementType;
import com.rudderstack.sourceapplication.exceptions.InvalidSourceException;
import com.rudderstack.sourceapplication.utils.ObjectMapperUtil;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class InputValidator implements ElementValidator {
    private final ObjectMapper objectMapper;
    public InputValidator() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void parseAndValidateRequestBody(Object request) {
        Input input = ObjectMapperUtil.parse(request, Input.class, objectMapper);
    }
    @Override
    public void validateValue(Object request, Object value){
        Input input = ObjectMapperUtil.parse(request, Input.class, objectMapper);
        if(input.isRequired() && Objects.isNull(value)){
            throw new InvalidSourceException("Input validation failed");
        }
        if(!Objects.isNull(value)){
            String inputValue = (String) value;
            String regex = input.getRegex();
            Pattern regexPattern = Pattern.compile(regex);
            Matcher regexMatcher = regexPattern.matcher(inputValue);
            if(!regexMatcher.matches()){
                throw new InvalidSourceException("Input value did not match regex");
            }
        }
    }

    @Override
    public ElementType getElementType() {
        return ElementType.INPUT;
    }
}
