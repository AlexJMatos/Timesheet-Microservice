package com.employees.management.ksquare.timesheet.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {
    private Long timestamp;
    private Integer statusCode;
    private String errorMessage;
}
