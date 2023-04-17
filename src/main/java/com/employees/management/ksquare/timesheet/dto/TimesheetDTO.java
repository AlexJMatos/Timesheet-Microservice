package com.employees.management.ksquare.timesheet.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TimesheetDTO  {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double weekHours;
    private EmployeeDTO requester;
    private List<TimesheetProjectDTO> projects;
}