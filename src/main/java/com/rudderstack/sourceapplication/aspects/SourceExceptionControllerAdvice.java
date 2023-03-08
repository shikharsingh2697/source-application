package com.rudderstack.sourceapplication.aspects;

import com.rudderstack.sourceapplication.constants.ErrorConstants;
import com.rudderstack.sourceapplication.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;

@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SourceExceptionControllerAdvice {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Problem onBadRequestException(BadRequestException e) {
        log.error(e.toString(), e);
        return Problem.builder()
                .withTitle(ErrorConstants.BAD_REQUEST)
                .withDetail(e.getMessage())
                .build();
    }

    @ExceptionHandler(DeserializationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Problem onDeserializationException(DeserializationException e) {
        log.warn(e.getMessage(), e);
        return Problem.builder()
                .withTitle(ErrorConstants.DESERIALIZATION_EXCEPTION)
                .withDetail(e.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidElementTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Problem onInvalidElementTypeException(InvalidElementTypeException e) {
        log.warn(e.getMessage(), e);
        return Problem.builder()
                .withTitle(ErrorConstants.INVALID_ELEMENT_TYPE_EXCEPTION)
                .withDetail(e.getMessage())
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Problem onResourceNotFoundException(ResourceNotFoundException e) {
        log.warn(e.getMessage(), e);
        return Problem.builder()
                .withTitle(ErrorConstants.RESOURCE_NOT_FOUND_EXCEPTION)
                .withDetail(e.getMessage())
                .build();
    }

    @ExceptionHandler(SerializeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Problem onSerializeException(SerializeException e) {
        log.warn(e.getMessage(), e);
        return Problem.builder()
                .withTitle(ErrorConstants.SERIALIZATION_EXCEPTION)
                .withDetail(e.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidSourceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Problem onInvalidSourceException(InvalidSourceException e) {
        log.warn(e.getMessage(), e);
        return Problem.builder()
                .withTitle(ErrorConstants.INVALID_SOURCE_EXCEPTION)
                .withDetail(e.getMessage())
                .build();
    }
}