package com.employees.management.ksquare.timesheet.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProjectDTO {
    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    private String projectOwner;
    private EmployeeDTO pmo;
    private BigDecimal costUSD;
}