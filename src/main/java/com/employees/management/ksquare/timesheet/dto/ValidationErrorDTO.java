package com.employees.management.ksquare.timesheet.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorDTO {
    private Long timestamp;
    private Integer statusCode;
    private Set<String> validationFields;
    private List<String> validationErrors;
}
