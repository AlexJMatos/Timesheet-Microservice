package com.employees.management.ksquare.timesheet.dto;

import com.employees.management.ksquare.timesheet.entity.enumerators.TimesheetProjectStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimesheetProjectDTO {
    private UUID id;
    private ProjectDTO project;
    private TimesheetProjectStatus status;
    private double projectHours;
    private String comment;
    private EmployeeDTO approves;
    private ProjectHoursDTO hours;
    private ProjectHoursDTO extraHours;
}
