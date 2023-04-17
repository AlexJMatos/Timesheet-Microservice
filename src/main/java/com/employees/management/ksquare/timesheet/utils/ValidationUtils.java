package com.employees.management.ksquare.timesheet.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidationUtils {
    public List<String> extractValidationErrors(List<ObjectError> errors) {
        return errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
    }

    public Set<String> extractValidationFields(List<FieldError> errors) {
        return errors.stream().map(FieldError::getField).collect(Collectors.toSet());
    }
}
