package com.rudderstack.sourceapplication.utils;

import com.rudderstack.sourceapplication.enums.ElementType;
import com.rudderstack.sourceapplication.exceptions.InvalidElementTypeException;
import com.rudderstack.sourceapplication.service.validators.ElementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class FormElementUtil {
    private final Map<ElementType, ElementValidator> formElements;

    @Autowired
    public FormElementUtil(List<ElementValidator> formElements) {
        this.formElements =
                formElements.stream()
                        .collect(Collectors.toMap(ElementValidator::getElementType, Function.identity()));
    }

    public ElementValidator getFormElementValidator(ElementType elementType) {
        return Optional.ofNullable(formElements.get(elementType))
                .orElseThrow(() -> new InvalidElementTypeException("Element type is invalid"));
    }
}
