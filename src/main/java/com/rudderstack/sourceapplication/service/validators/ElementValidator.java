package com.rudderstack.sourceapplication.service.validators;

import com.rudderstack.sourceapplication.enums.ElementType;

public interface ElementValidator {
    void parseAndValidateRequestBody(Object request);
    ElementType getElementType();
    void validateValue(Object request, Object value);
}