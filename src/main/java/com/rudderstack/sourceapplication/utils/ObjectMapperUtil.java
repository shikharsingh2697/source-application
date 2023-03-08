package com.rudderstack.sourceapplication.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudderstack.sourceapplication.exceptions.DeserializationException;
import com.rudderstack.sourceapplication.exceptions.SerializeException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ObjectMapperUtil {
    public static <T> String stringify(T obj, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Failed to stringify object: {}. Exception: {}", obj, e);

            throw new SerializeException("Unable to serialize");
        }
    }

    public static <S, T> T parse(S obj, Class<T> clazz, ObjectMapper objectMapper) {
        try {
            log.info("Parsing class {} for object {}", clazz, obj);

            if (obj instanceof String) {
                return objectMapper.readValue((String) obj, clazz);
            }

            return objectMapper.convertValue(obj, clazz);
        } catch (IllegalArgumentException | IOException e) {
            log.error("Failed to parse the given object: {}. Exception: ", obj, e);

            throw new DeserializationException("Unable to deserialize");
        }
    }
}