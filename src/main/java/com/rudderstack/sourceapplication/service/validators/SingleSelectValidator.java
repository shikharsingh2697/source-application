package com.rudderstack.sourceapplication.service.validators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudderstack.sourceapplication.domains.elements.SingleSelect;
import com.rudderstack.sourceapplication.domains.elements.SingleSelectOption;
import com.rudderstack.sourceapplication.enums.ElementType;
import com.rudderstack.sourceapplication.exceptions.InvalidSourceException;
import com.rudderstack.sourceapplication.utils.ObjectMapperUtil;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SingleSelectValidator implements ElementValidator {
    private final ObjectMapper objectMapper;
    public SingleSelectValidator() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void parseAndValidateRequestBody(Object request) {
        SingleSelect singleSelect = ObjectMapperUtil.parse(request, SingleSelect.class, objectMapper);
    }
    @Override
    public void validateValue(Object request, Object value){
        SingleSelect singleSelect = ObjectMapperUtil.parse(request, SingleSelect.class, objectMapper);
        if(singleSelect.isRequired() && Objects.isNull(value)){
            throw new InvalidSourceException("SingleSelect validation failed");
        }
        if(!Objects.isNull(value)){
            String selectValue = (String) value;
            Set<String> selectOptions = singleSelect.getOptions().stream().map(SingleSelectOption::getValue).collect(Collectors.toSet());
            if(!selectOptions.contains(selectValue)){
                throw new InvalidSourceException("Invalid option selected");
            }
        }
    }

    @Override
    public ElementType getElementType() {
        return ElementType.SINGLE_SELECT;
    }
}
