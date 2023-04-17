package com.employees.management.ksquare.timesheet.controller.exception;

import com.employees.management.ksquare.timesheet.dto.ErrorDTO;
import com.employees.management.ksquare.timesheet.dto.ValidationErrorDTO;
import com.employees.management.ksquare.timesheet.exception.EntityNotFoundException;
import com.employees.management.ksquare.timesheet.utils.ValidationUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private ValidationUtils utils;

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorDTO> handleEntityNotFoundException(RuntimeException ex) {
        ErrorDTO error = ErrorDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorMessage(ex.getMessage())
                .timestamp(Instant.now().toEpochMilli())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(ValidationErrorDTO.builder()
                .statusCode(status.value())
                .timestamp(Instant.now().toEpochMilli())
                .validationFields(utils.extractValidationFields(ex.getFieldErrors()))
                .validationErrors(utils.extractValidationErrors(ex.getAllErrors()))
                .build(), status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorDTO> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.info("Validation errors: {}", ex.getMostSpecificCause().getMessage());
        ErrorDTO error = ErrorDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorMessage(Arrays.stream(ex.getMostSpecificCause().getMessage().split("\"")).findFirst().orElse(""))
                .timestamp(Instant.now().toEpochMilli())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
