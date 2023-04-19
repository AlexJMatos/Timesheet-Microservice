package com.employees.management.ksquare.timesheet.utils;

import jakarta.validation.ConstraintViolation;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class ValidationUtils {
    public List<String> extractValidationErrors(List<ObjectError> errors) {
        return errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
    }

    public Set<String> extractValidationFields(List<FieldError> errors) {
        return errors.stream().map(FieldError::getField).collect(Collectors.toSet());
    }

    public List<String> extractValidationErrors(Set<ConstraintViolation<?>> errors) {
        return errors.stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.toList());
    }

    public Set<String> extractValidationFields(Set<ConstraintViolation<?>> errors) {
        return errors.stream()
                .map(constraintViolation -> Objects.requireNonNull(StreamSupport.stream(constraintViolation.getPropertyPath().spliterator(), false)
                        .reduce((first, second) -> second).orElse(null)).getName())
                .collect(Collectors.toSet());
    }
}
