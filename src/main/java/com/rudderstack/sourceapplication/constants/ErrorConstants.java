package com.rudderstack.sourceapplication.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String DESERIALIZATION_EXCEPTION = "DESERIALIZATION_EXCEPTION";
    public static final String SERIALIZATION_EXCEPTION = "SERIALIZATION_EXCEPTION";
    public static final String INVALID_ELEMENT_TYPE_EXCEPTION = "INVALID_ELEMENT_TYPE_EXCEPTION";
    public static final String RESOURCE_NOT_FOUND_EXCEPTION = "RESOURCE_NOT_FOUND_EXCEPTION";
    public static final String INVALID_SOURCE_EXCEPTION = "INVALID_SOURCE_EXCEPTION";
}
